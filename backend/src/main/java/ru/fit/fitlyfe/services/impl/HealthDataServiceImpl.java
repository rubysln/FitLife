package ru.fit.fitlyfe.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fit.fitlyfe.exceptions.HealthDataBadRequestException;
import ru.fit.fitlyfe.exceptions.HealthDataNotFoundException;
import ru.fit.fitlyfe.models.HealthData;
import ru.fit.fitlyfe.models.UserProfile;
import ru.fit.fitlyfe.repository.HealthDataRepository;
import ru.fit.fitlyfe.repository.UserProfileRepository;
import ru.fit.fitlyfe.services.HealthDataService;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class HealthDataServiceImpl implements HealthDataService {

    private final UserProfileRepository userProfileRepository;
    private final HealthDataRepository healthDataRepository;
    private static final String BAD_REQUEST_HEALTH = "Неверная форма данных о здоровье!";
    private static final String NOT_FOUND_USER = "Пользователь не найден!";
    private static final String NOT_FOUND_HEALTH = "Данных о здоровье не найдено!";
    private static final String ANOTHER_USER_HEALTH = "Данные принадлежат другому пользователю!";

    @Autowired
    public HealthDataServiceImpl(UserProfileRepository userProfileRepository,
                                 HealthDataRepository healthDataRepository) {
        this.userProfileRepository = userProfileRepository;
        this.healthDataRepository = healthDataRepository;
    }

    @Override
    public List<HealthData> getAllHealth(Long userId) {
        try {
            Optional<UserProfile> userOptional = userProfileRepository.findById(userId);
            if (userOptional.isPresent())
                return userOptional.get().getHealthDataList();
            else
                throw new HealthDataNotFoundException(NOT_FOUND_USER);
        } catch (RuntimeException exception) {
            throw new HealthDataNotFoundException(NOT_FOUND_HEALTH);
        }
    }

    @Override
    public Optional<HealthData> getOneHealth(Long userId, Long healthId) {
        var userOptional = userProfileRepository.findById(userId);
        var healthOptional = healthDataRepository.findById(healthId);

        if(userOptional.isPresent()){
            if(healthOptional.isPresent()){
                if(userOptional.get().getHealthDataList().contains(healthOptional.get())){
                    return healthOptional;
                }
                else
                    throw new HealthDataNotFoundException(ANOTHER_USER_HEALTH);
            }
            else throw new HealthDataNotFoundException(NOT_FOUND_HEALTH);
        }
        else
            throw new HealthDataNotFoundException(NOT_FOUND_USER);
    }

    @Override
    public HealthData createHealth(HealthData healthData, Long userId) {
        var userOptional = userProfileRepository.findById(userId);

        if (userOptional.isPresent()) {
            healthData.setUserProfile(userOptional.get());
            return healthDataRepository.save(healthData);
        } else
            throw new HealthDataBadRequestException(BAD_REQUEST_HEALTH);
    }

    @Override
    public Optional<HealthData> patchHealth(HealthData health, Long healthId) {
        try{
        return healthDataRepository.findById(healthId)
                .map(healthData -> {
                    healthData.setUserProfile(health.getUserProfile());
                    healthData.setBloodPressure(health.getBloodPressure());
                    healthData.setHeartRate(health.getHeartRate());
                    healthData.setBloodSugarLevel(health.getBloodSugarLevel());
                    return healthDataRepository.save(healthData);
                });
        } catch (RuntimeException exception){
            throw new HealthDataBadRequestException(BAD_REQUEST_HEALTH);
        }
    }

    @Override
    public void deleteHealth(Long healthId) {
        try {
            healthDataRepository.deleteById(healthId);
        }
        catch (RuntimeException exception){
            throw new HealthDataNotFoundException(NOT_FOUND_HEALTH);
        }
    }
}