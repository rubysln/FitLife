package ru.fit.fitlyfe.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fit.fitlyfe.exceptions.PhysicalActivityDataBadRequestException;
import ru.fit.fitlyfe.exceptions.PhysicalActivityDataNotFoundException;
import ru.fit.fitlyfe.models.PhysicalActivityData;
import ru.fit.fitlyfe.models.UserProfile;
import ru.fit.fitlyfe.repository.PhysicalActivityDataRepository;
import ru.fit.fitlyfe.repository.UserProfileRepository;
import ru.fit.fitlyfe.services.PhysicalActivityDataService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhysicalActivityDataServiceImpl implements PhysicalActivityDataService {
    private static final String NOT_FOUND_USER = "Пользователь не найден!";
    private static final String NOT_FOUND_ACTIVITY = "Данных об активности не найдено!";
    private static final String BAD_REQUEST_ACTIVITY = "Ошибка формы активности!";

    private final UserProfileRepository userProfileRepository;
    private final PhysicalActivityDataRepository physicalActivityDataRepository;

    @Autowired
    public PhysicalActivityDataServiceImpl(UserProfileRepository userProfileRepository,
                                           PhysicalActivityDataRepository physicalActivityDataRepository) {
        this.userProfileRepository = userProfileRepository;
        this.physicalActivityDataRepository = physicalActivityDataRepository;
    }

    @Override
    public List<PhysicalActivityData> getPhysicalActivityDataListById(Long userId, Long activityId) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findById(userId);

        if (userProfileOptional.isPresent()) {
            UserProfile userProfile = userProfileOptional.get();
            try {
                List<PhysicalActivityData> physicalActivityDataList = userProfile.getPhysicalActivityDataList();
                return physicalActivityDataList.stream()
                        .filter(activityData -> activityData.getDataId().equals(activityId))
                        .collect(Collectors.toList());
            } catch (RuntimeException exception) {
                throw new PhysicalActivityDataNotFoundException(NOT_FOUND_ACTIVITY);
            }
        } else {
            throw new PhysicalActivityDataNotFoundException(NOT_FOUND_USER);
        }

    }

    @Override
    public List<PhysicalActivityData> getPhysicalActivityDataList(Long userId) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findById(userId);

        if (userProfileOptional.isPresent()) {
            UserProfile userProfile = userProfileOptional.get();
            try {
                return userProfile.getPhysicalActivityDataList();
            } catch (RuntimeException exception) {
                throw new PhysicalActivityDataNotFoundException(NOT_FOUND_ACTIVITY);
            }
        } else {
            throw new PhysicalActivityDataNotFoundException(NOT_FOUND_USER);
        }
    }

    @Override
    public PhysicalActivityData createActivity(PhysicalActivityData physicalActivityData,
                                               Long userId) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findById(userId);

        if (userProfileOptional.isPresent()) {
            UserProfile user = userProfileOptional.get();

            try {
                physicalActivityData.setUserProfile(user);
                return physicalActivityDataRepository.save(physicalActivityData);
            } catch (RuntimeException exception) {
                throw new PhysicalActivityDataBadRequestException(BAD_REQUEST_ACTIVITY);
            }
        } else {
            throw new PhysicalActivityDataNotFoundException(NOT_FOUND_USER);
        }
    }

    @Override
    public Optional<PhysicalActivityData> patchActivity(PhysicalActivityData newPhysicalActivityData,
                                                        Long userId, Long activityId) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findById(userId);
        if (userProfileOptional.isPresent()) {
            try {
                return userProfileOptional.flatMap(user -> {
                    Optional<PhysicalActivityData> physicalActivityDataOptional = user.getPhysicalActivityDataList()
                            .stream()
                            .filter(activity -> activity.getDataId().equals(activityId))
                            .findFirst();

                    return physicalActivityDataOptional.map(activity -> {
                        if (newPhysicalActivityData.getSteps() != null) {
                            activity.setSteps(newPhysicalActivityData.getSteps());
                        }
                        if (newPhysicalActivityData.getDistance() != null) {
                            activity.setDistance(newPhysicalActivityData.getDistance());
                        }
                        if (newPhysicalActivityData.getWorkoutTime() != null) {
                            activity.setWorkoutTime(newPhysicalActivityData.getWorkoutTime());
                        }
                        if (newPhysicalActivityData.getCaloriesBurned() != null) {
                            activity.setCaloriesBurned(newPhysicalActivityData.getCaloriesBurned());
                        }

                        return physicalActivityDataRepository.save(activity);
                    });
                });
            } catch (RuntimeException exception) {
                throw new PhysicalActivityDataBadRequestException(BAD_REQUEST_ACTIVITY);
            }
        } else throw new PhysicalActivityDataNotFoundException(NOT_FOUND_USER);
    }

    public void deleteById(Long activityId) {
        try {
            physicalActivityDataRepository.deleteById(activityId);
        } catch (RuntimeException exception) {
            throw new PhysicalActivityDataNotFoundException(NOT_FOUND_ACTIVITY);
        }
    }
}
