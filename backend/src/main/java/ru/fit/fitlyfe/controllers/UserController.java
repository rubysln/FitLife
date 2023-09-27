package ru.fit.fitlyfe.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
import ru.fit.fitlyfe.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 4800)
@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserRepository repository;

	@GetMapping(value = "/{id}")
	Optional<UserProfile> getUser(@PathVariable("id") long id){
		return repository.findById(id);
	}

	@GetMapping(value = "/")
	List<UserProfile> getUsers(){
		return repository.findAll();
	}

	@PostMapping("/")
	UserProfile createUser(@RequestBody UserProfile userProfile){
		return repository.save(userProfile);
	}

	@DeleteMapping("/{id}")
	void deleteUser(@PathVariable("id") long id){
		repository.deleteById(id);
	}

	@PatchMapping("/{id}")
	Optional<UserProfile> patchUser(@PathVariable("id") long id, @RequestBody UserProfile userProfile){
		return repository.findById(id)
				.map(user -> {
					user.setUsername(userProfile.getUsername());
					user.setEmail(userProfile.getEmail());
					user.setDate(userProfile.getEmail());
					user.setPassword_hash(userProfile.getPassword_hash());
					user.setHeight(userProfile.getHeight());
					user.setWeight(userProfile.getWeight());
					return repository.save(user);
				});
	}

	public UserRepository getRepository(){
		return repository;
	}
}