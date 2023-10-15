package ru.fit.fitlyfe.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fit.fitlyfe.exceptions.UserExceptionBadRequest;
import ru.fit.fitlyfe.exceptions.UserExceptionNotFound;
import ru.fit.fitlyfe.models.UserProfile;
import ru.fit.fitlyfe.repository.UserProfileRepository;
import ru.fit.fitlyfe.services.UserProfileService;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private static final String NOT_FOUND_USER = "Пользователь не найден!";
    private static final String NOT_FOUND_LIST_USERS = "Список пользователей пуст!";
    private static final String BAD_REQUEST_USER = "Ошибка формы пользователя!";

    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public Optional<UserProfile> getOneUser(Long id) {
        var user = userProfileRepository.findById(id);
        if (user.isPresent()) return user;
        else throw new UserExceptionNotFound(NOT_FOUND_USER);
    }

    @Override
    public List<UserProfile> getAllUsers() {
        var users = userProfileRepository.findAll();
        if (users.isEmpty()) throw new UserExceptionNotFound(NOT_FOUND_LIST_USERS);
        else return users;
    }

    @Override
    public UserProfile createUser(UserProfile userProfile) {
        try {
            return userProfileRepository.save(userProfile);
        } catch (RuntimeException exception) {
            throw new UserExceptionBadRequest(BAD_REQUEST_USER);
        }
    }

    @Override
    public void deleteUser(Long id) {
        try {
            userProfileRepository.deleteById(id);
        } catch (RuntimeException exception) {
            throw new UserExceptionBadRequest(NOT_FOUND_USER);
        }
    }

    @Override
    public Optional<UserProfile> patchUser(Long id, UserProfile userProfile) {
        try {
            return userProfileRepository.findById(id)
                    .map(user -> {
                        user.setHeight(userProfile.getHeight());
                        user.setWeight(userProfile.getWeight());
                        user.setEmail(userProfile.getEmail());
                        user.setDate(userProfile.getDate());
                        user.setUsername(userProfile.getUsername());
                        return user;
                    });
        } catch (RuntimeException exception) {
            throw new UserExceptionBadRequest(BAD_REQUEST_USER);
        }
    }
}
