package ru.fit.fitlyfe.services;

import ru.fit.fitlyfe.models.PhysicalActivityData;

import java.util.List;
import java.util.Optional;

public interface PhysicalActivityDataService {
    List<PhysicalActivityData> getPhysicalActivityDataListById(Long userId, Long activityId);

    List<PhysicalActivityData> getPhysicalActivityDataList(Long userId);

    PhysicalActivityData createActivity(PhysicalActivityData physicalActivityData, Long id);

    Optional<PhysicalActivityData> patchActivity(PhysicalActivityData newPhysicalActivityData, Long userId, Long activityId);

    boolean deleteById(Long activityId);
}
