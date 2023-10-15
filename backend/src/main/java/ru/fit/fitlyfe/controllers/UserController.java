package ru.fit.fitlyfe.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fit.fitlyfe.exceptions.UserExceptionBadRequest;
import ru.fit.fitlyfe.exceptions.UserExceptionNotFound;
import ru.fit.fitlyfe.models.UserProfile;
import ru.fit.fitlyfe.services.impl.UserProfileServiceImpl;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserProfileServiceImpl userProfileService;

    @GetMapping("/{id}")
    ResponseEntity<UserProfile> getOneUser(@PathVariable("id") Long id) {
        Optional<UserProfile> userProfile = userProfileService.getOneUser(id);
        return ResponseEntity.ok(userProfile.get());
    }


    @GetMapping
    List<UserProfile> getAllUsers() {
        return userProfileService.getAllUsers();
    }

    @PostMapping
    ResponseEntity<UserProfile> createUser(@RequestBody UserProfile userProfile) {
        var user = userProfileService.createUser(userProfile);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        var user = userProfileService.getOneUser(id);
        userProfileService.deleteUser(id);
        return ResponseEntity.ok("Пользователь с Id: " + id + " удалён!");
    }

    @PatchMapping("/{id}")
    ResponseEntity<UserProfile> patchUser(@PathVariable("id") Long id,
                                     @RequestBody UserProfile userProfile) {
        var user = userProfileService.getOneUser(id);
        return ResponseEntity.ok(userProfileService.patchUser(id, userProfile).get());
    }

    @ExceptionHandler(UserExceptionNotFound.class)
    public ResponseEntity<String> notFoundException(UserExceptionNotFound exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(UserExceptionBadRequest.class)
    public ResponseEntity<String> badRequestException(UserExceptionBadRequest exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}