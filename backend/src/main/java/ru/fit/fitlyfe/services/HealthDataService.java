package ru.fit.fitlyfe.services;

import ru.fit.fitlyfe.models.HealthData;

import java.util.List;
import java.util.Optional;

public interface HealthDataService {
    List<HealthData> getAllHealth(Long userId);

    Optional<HealthData> getOneHealth(Long userId, Long healthId);

    HealthData createHealth(HealthData healthData, Long userId);

    Optional<HealthData> patchHealth(HealthData healthData, Long healthId);

    void deleteHealth(Long healthId);
}
