package ru.fit.fitlyfe.services.impl;

import org.springframework.stereotype.Service;
import ru.fit.fitlyfe.models.HealthData;
import ru.fit.fitlyfe.services.HealthDataService;

import java.util.List;
import java.util.Optional;

@Service
public class HealthDataServiceImpl implements HealthDataService {
    @Override
    public List<HealthData> getAllHealth(Long userId) {
        return null;
    }

    @Override
    public Optional<HealthData> getOneHealth(Long userId, Long healthId) {
        return Optional.empty();
    }

    @Override
    public HealthData createHealth(HealthData healthData, Long userId) {
        return null;
    }

    @Override
    public Optional<HealthData> patchHealth(HealthData healthData, Long healthId) {
        return Optional.empty();
    }

    @Override
    public void deleteHealth(Long healthId) {

    }
}
