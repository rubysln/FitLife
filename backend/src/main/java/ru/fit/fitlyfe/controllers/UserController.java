package ru.fit.fitlyfe.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fit.fitlyfe.models.UserProfile;
import ru.fit.fitlyfe.services.impl.UserProfileServiceImpl;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserProfileServiceImpl userProfileService;

	@GetMapping("/{id}")
	Optional<UserProfile> getOneUser(@PathVariable("id") Long id) {
		return userProfileService.getOneUser(id);
	}


	@GetMapping
	List<UserProfile> getAllUsers() {
		return userProfileService.getAllUsers();
	}

	@PostMapping
	UserProfile createUser(@RequestBody UserProfile userProfile) {
		return userProfileService.createUser(userProfile);
	}

	@DeleteMapping("/{id}")
	void deleteUser(@PathVariable("id") Long id) {
		userProfileService.deleteUser(id);
	}

	@PatchMapping("/{id}")
	Optional<UserProfile> patchUser(@PathVariable("id") Long id,
			@RequestBody UserProfile userProfile) {
		return userProfileService.patchUser(id, userProfile);
	}
}