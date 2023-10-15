package ru.fit.fitlyfe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fit.fitlyfe.exceptions.PhysicalActivityDataBadRequestException;
import ru.fit.fitlyfe.exceptions.PhysicalActivityDataNotFoundException;
import ru.fit.fitlyfe.models.PhysicalActivityData;
import ru.fit.fitlyfe.services.impl.PhysicalActivityDataServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/activities")
public class PhysicalActivityDataController {

    @Autowired
    private PhysicalActivityDataServiceImpl physicalActivityDataService;

    @GetMapping("/{userId}/{activityId}")
    public EntityModel<Object> getOneActivity(@PathVariable Long userId,
                                              @PathVariable Long activityId) {
        PhysicalActivityData activityData = physicalActivityDataService.getPhysicalActivityDataListById(
                userId, activityId).get(0);
        Link selfLink = linkTo(methodOn(PhysicalActivityDataController.class)
                .getOneActivity(userId, activityId))
                .withSelfRel();
        return EntityModel.of(activityData, selfLink);
    }

    @GetMapping("/{userId}")
    public CollectionModel<EntityModel<PhysicalActivityData>> getAllActivity(
            @PathVariable Long userId) {
        List<PhysicalActivityData> activityDataList = physicalActivityDataService.getPhysicalActivityDataList(
                userId);

        List<EntityModel<PhysicalActivityData>> activityModels = activityDataList.stream()
                .map(activityData -> EntityModel.of(activityData,
                        linkTo(methodOn(PhysicalActivityDataController.class)
                                .getOneActivity(userId, activityData.getDataId()))
                                .withSelfRel()))
                .collect(Collectors.toList());

        Link collectionLink = linkTo(methodOn(PhysicalActivityDataController.class)
                .getAllActivity(userId))
                .withSelfRel();

        return CollectionModel.of(activityModels, collectionLink);
    }

    @PostMapping("/{userId}")
    public EntityModel<PhysicalActivityData> createActivity(
            @RequestBody PhysicalActivityData physicalActivityData, @PathVariable Long userId) {
        PhysicalActivityData createdActivity = physicalActivityDataService.createActivity(
                physicalActivityData, userId);

        Link selfLink = linkTo(methodOn(PhysicalActivityDataController.class)
                .getOneActivity(userId, createdActivity.getDataId()))
                .withSelfRel();

        return EntityModel.of(createdActivity, selfLink);
    }

    @PatchMapping("/{userId}/{activityId}")
    public EntityModel<Optional<PhysicalActivityData>> patchActivity(
            @RequestBody PhysicalActivityData newPhysicalActivityData, @PathVariable Long userId,
            @PathVariable Long activityId) {
        Optional<PhysicalActivityData> patchedActivity = physicalActivityDataService.patchActivity(
                newPhysicalActivityData, userId, activityId);

        Link selfLink = linkTo(methodOn(PhysicalActivityDataController.class)
                .getOneActivity(userId, activityId))
                .withSelfRel();

        return EntityModel.of(patchedActivity, selfLink);
    }

    @DeleteMapping("/{activityId}")
    public ResponseEntity<String> deleteMapping(@PathVariable Long activityId) {
        physicalActivityDataService.deleteById(activityId);
        return ResponseEntity.ok("Активность с ID: " + activityId + " удалена!");
    }

    @ExceptionHandler(PhysicalActivityDataBadRequestException.class)
    public ResponseEntity<String> badRequestException(PhysicalActivityDataBadRequestException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(PhysicalActivityDataNotFoundException.class)
    public ResponseEntity<String> notFoundException(PhysicalActivityDataNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
