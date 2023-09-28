package ru.fit.fitlyfe.services.impl;

import org.springframework.stereotype.Service;
import ru.fit.fitlyfe.models.UserProfile;
import ru.fit.fitlyfe.repository.UserProfileRepository;
import ru.fit.fitlyfe.services.UserProfileService;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	private final UserProfileRepository userProfileRepository;

	public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
		this.userProfileRepository = userProfileRepository;
	}

	@Override
	public Optional<UserProfile> getOneUser(Long id) {
		return userProfileRepository.findById(id);
	}

	@Override
	public List<UserProfile> getAllUsers() {
		return userProfileRepository.findAll();
	}

	@Override
	public UserProfile createUser(UserProfile userProfile) {
		return userProfileRepository.save(userProfile);
	}

	@Override
	public void deleteUser(Long id) {
		userProfileRepository.deleteById(id);
	}

	@Override
	public Optional<UserProfile> patchUser(Long id, UserProfile userProfile) {
		return userProfileRepository.findById(id)
				.map(user -> {
					user.setHeight(userProfile.getHeight());
					user.setWeight(userProfile.getWeight());
					user.setEmail(userProfile.getEmail());
					user.setDate(userProfile.getDate());
					user.setUsername(userProfile.getUsername());
					return user;
				});
	}
}
