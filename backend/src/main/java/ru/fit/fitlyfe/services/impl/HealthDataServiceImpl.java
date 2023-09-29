package ru.fit.fitlyfe.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fit.fitlyfe.models.HealthData;
import ru.fit.fitlyfe.models.UserProfile;
import ru.fit.fitlyfe.repository.HealthDataRepository;
import ru.fit.fitlyfe.repository.UserProfileRepository;
import ru.fit.fitlyfe.services.HealthDataService;

import java.util.List;
import java.util.Optional;

@Service
public class HealthDataServiceImpl implements HealthDataService {

	private final UserProfileRepository userProfileRepository;
	private final HealthDataRepository healthDataRepository;

	@Autowired
	public HealthDataServiceImpl(UserProfileRepository userProfileRepository,
			HealthDataRepository healthDataRepository) {
		this.userProfileRepository = userProfileRepository;
		this.healthDataRepository = healthDataRepository;
	}

	@Override
	public List<HealthData> getAllHealth(Long userId) {
		Optional<UserProfile> userOptional = userProfileRepository.findById(userId);
		if (userOptional.isPresent()) {
			var user = userOptional.get();

			if (!user.getHealthDataList().isEmpty()) {
				return user.getHealthDataList();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public Optional<HealthData> getOneHealth(Long userId, Long healthId) {
		var userOptional = userProfileRepository.findById(userId);
		var healthOptional = healthDataRepository.findById(healthId);

		if (userOptional.isPresent() && healthOptional.isPresent()) {
			var user = userOptional.get();
			var health = healthOptional.get();
			if (user.getHealthDataList().contains(health)) {
				return healthOptional;
			} else {
				return Optional.empty();
			}
		} else {
			return Optional.empty();
		}
	}

	@Override
	public HealthData createHealth(HealthData healthData, Long userId) {
		var userOptional = userProfileRepository.findById(userId);

		if (userOptional.isPresent()) {
			healthData.setUserProfile(userOptional.get());
			return healthDataRepository.save(healthData);
		} else {
			return null;
		}
	}

	@Override
	public Optional<HealthData> patchHealth(HealthData health, Long healthId) {
		return healthDataRepository.findById(healthId)
				.map(healthData -> {
					healthData.setUserProfile(health.getUserProfile());
					healthData.setBloodPressure(health.getBloodPressure());
					healthData.setHeartRate(health.getHeartRate());
					healthData.setBloodSugarLevel(health.getBloodSugarLevel());
					return healthDataRepository.save(healthData);
				});
	}

	@Override
	public void deleteHealth(Long healthId) {
		healthDataRepository.deleteById(healthId);
	}
}
