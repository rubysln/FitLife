package ru.fit.fitlyfe.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.fit.fitlyfe.exceptions.AuthenticationBadRequestException;
import ru.fit.fitlyfe.exceptions.RegisterBadRequestException;
import ru.fit.fitlyfe.models.UserProfile;
import ru.fit.fitlyfe.repository.UserProfileRepository;
import ru.fit.fitlyfe.security.AuthenticationRequest;
import ru.fit.fitlyfe.security.AuthenticationResponse;
import ru.fit.fitlyfe.security.RegisterRequest;
import ru.fit.fitlyfe.services.AuthenticationService;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserProfileRepository userProfileRepository;
    private final JwtServiceImpl jwtService;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationServiceImpl(UserProfileRepository userProfileRepository,
                                     JwtServiceImpl jwtService,
                                     AuthenticationManager authenticationManager) {
        this.userProfileRepository = userProfileRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        try{
            String encodedPassword = new BCryptPasswordEncoder().encode(request.getPassword());
            var user = new UserProfile();
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setPasswordHash(encodedPassword);
            userProfileRepository.save(user);

            var token = jwtService.generateToken(user);

            return AuthenticationResponse
                .builder()
                .token(token)
                .build();
        }
        catch (RuntimeException exception){
            throw new RegisterBadRequestException("Ошибка при регистрации!");
        }
    }

    public AuthenticationResponse auth(AuthenticationRequest request) {
        try{
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
                )
            );
            var user = userProfileRepository.findUserProfileByUsername(request.getUsername());

            var token = jwtService.generateToken(user);

            return AuthenticationResponse
                .builder()
                .token(token)
                .build();
        } catch (RuntimeException runtimeException){
            throw new AuthenticationBadRequestException("Ошибка авторизации!");
        }
    }
}