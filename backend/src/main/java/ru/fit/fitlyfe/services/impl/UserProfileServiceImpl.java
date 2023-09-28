package ru.fit.fitlyfe.services.impl;

import org.springframework.stereotype.Service;
import ru.fit.fitlyfe.models.UserProfile;
import ru.fit.fitlyfe.services.UserProfileService;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Override
    public Optional<UserProfile> getOneUser(Long id) {
        return Optional.empty();
    }

    @Override
    public List<UserProfile> getAllUsers() {
        return null;
    }

    @Override
    public UserProfile createUser(UserProfile userProfile) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public Optional<UserProfile> patchUser(Long id, UserProfile userProfile) {
        return Optional.empty();
    }
}
