package ru.fit.fitlyfe.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fit.fitlyfe.models.HealthData;
import ru.fit.fitlyfe.models.UserProfile;
import ru.fit.fitlyfe.repository.HealthDataRepository;
import ru.fit.fitlyfe.repository.UserRepository;

@RestController
@RequestMapping("/api/health")
public class HealthDataController {

	@Autowired
	private HealthDataRepository repository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping(value = "/{userId}")
	List<HealthData> getHealths(@PathVariable("userId") long id) {
		Optional<UserProfile> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			var user = userOptional.get();

			if (!user.getHealthDataList().isEmpty()) {
				return user.getHealthDataList();
			} else {
				return null;
			}
		}
		else return null;
	}

	@GetMapping(value = "/{userId}/{healthId}")
	Optional<HealthData> getHealth(@PathVariable("userId") long userId, @PathVariable("healthId") long healthId){
		var userOptional = userRepository.findById(userId);
		var healthOptional = repository.findById(healthId);

		if(userOptional.isPresent() && healthOptional.isPresent()){
			var user = userOptional.get();
			var health = healthOptional.get();
			if(user.getHealthDataList().contains(health)) return healthOptional;
			else return Optional.empty();
		}
		else return Optional.empty();
	}

	@PostMapping(value = "/{userId}")
	HealthData createHealth(@PathVariable("userId") long userId, @RequestBody HealthData healthData){
		var userOptional = userRepository.findById(userId);

		if(userOptional.isPresent()){
			healthData.setUser_id(userOptional.get());
			return repository.save(healthData);
		}
		else return null;
	}

	@DeleteMapping(value = "/{healthId}")
	void deleteHealth(@PathVariable("healthId") long healthId){
		repository.deleteById(healthId);
	}

	@PatchMapping(value = "/{healthId}")
	Optional<HealthData> patchHealth(@PathVariable("healthId") long healthId, @RequestBody HealthData health){
		return repository.findById(healthId)
				.map(healthData -> {
					healthData.setUser_id(health.getUser_id());
					healthData.setBloodPressure(health.getBloodPressure());
					healthData.setHeartRate(health.getHeartRate());
					healthData.setBloodSugarLevel(health.getBloodSugarLevel());
					return repository.save(healthData);
				});
	}
}