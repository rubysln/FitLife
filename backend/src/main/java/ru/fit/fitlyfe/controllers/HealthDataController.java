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
import ru.fit.fitlyfe.repository.UserProfileRepository;
import ru.fit.fitlyfe.services.impl.HealthDataServiceImpl;

@RestController
@RequestMapping("/api/health")// ДОДЕЛАТЬ =) ДЕЛИГИРОВАНИЕ КОДА ЭТО ХОРОШО, работать напрямую с Repository = плохо, нужна абстракция в виде сервисов ^_^
public class HealthDataController {
	@Autowired
	private HealthDataServiceImpl healthDataService;
	@GetMapping("/{userId}")
	List<HealthData> getAllHealth(@PathVariable Long userId) {
		return healthDataService.getAllHealth(userId);
	}
	/*@GetMapping("/{userId}")
	List<HealthData> getHealths(@PathVariable("userId") long id) {
		Optional<UserProfile> userOptional = userProfileRepository.findById(id);
		if (userOptional.isPresent()) {
			var user = userOptional.get();

			if (!user.getHealthDataList().isEmpty()) {
				return user.getHealthDataList();
			} else {
				return null;
			}
		}
		else return null;
	}*/


	@GetMapping("/{userId}/{healthId}")
	Optional<HealthData> getOneHealth(@PathVariable Long userId, @PathVariable Long healthId){
		return healthDataService.getOneHealth(userId, healthId);
	}
	/*@GetMapping("/{userId}/{healthId}")
	Optional<HealthData> getOneHealth(@PathVariable("userId") long userId, @PathVariable("healthId") long healthId){
		var userOptional = userProfileRepository.findById(userId);
		var healthOptional = healthDataRepository.findById(healthId);

		if(userOptional.isPresent() && healthOptional.isPresent()){
			var user = userOptional.get();
			var health = healthOptional.get();
			if(user.getHealthDataList().contains(health)) return healthOptional;
			else return Optional.empty();
		}
		else return Optional.empty();
	}*/

	@PostMapping("/{userId}")
	HealthData createHealth(@RequestBody HealthData healthData, @PathVariable Long userId){
		return healthDataService.createHealth(healthData, userId);
	}
	/*@PostMapping("/{userId}")
	HealthData createHealth(@PathVariable("userId") long userId, @RequestBody HealthData healthData){
		var userOptional = userProfileRepository.findById(userId);

		if(userOptional.isPresent()){
			healthData.setUserProfile(userOptional.get());
			return healthDataRepository.save(healthData);
		}
		else return null;
	}*/


	@PatchMapping( "/{healthId}")
	Optional<HealthData> patchHealth(@RequestBody HealthData healthData, @PathVariable Long healthId){
		return healthDataService.patchHealth(healthData, healthId);
	}

	/*@PatchMapping( "/{healthId}")
	Optional<HealthData> patchHealth(@PathVariable("healthId") long healthId, @RequestBody HealthData health){
		return healthDataRepository.findById(healthId)
				.map(healthData -> {
					healthData.setUserProfile(health.getUserProfile());
					healthData.setBloodPressure(health.getBloodPressure());
					healthData.setHeartRate(health.getHeartRate());
					healthData.setBloodSugarLevel(health.getBloodSugarLevel());
					return healthDataRepository.save(healthData);
				});
	}*/


	@DeleteMapping("/{healthId}")
	void deleteHealth(@PathVariable Long healthId){
        healthDataService.deleteHealth(healthId);
    }
	/*@DeleteMapping("/{healthId}")
	void deleteHealth(@PathVariable("healthId") long healthId){
		healthDataRepository.deleteById(healthId);
	}*/
}
