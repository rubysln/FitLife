package ru.fit.fitlyfe.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fit.fitlyfe.exceptions.ApiError;
import ru.fit.fitlyfe.exceptions.AuthenticationBadRequestException;
import ru.fit.fitlyfe.exceptions.ErrorResponse;
import ru.fit.fitlyfe.exceptions.RegisterBadRequestException;
import ru.fit.fitlyfe.security.AuthenticationRequest;
import ru.fit.fitlyfe.security.AuthenticationResponse;
import ru.fit.fitlyfe.security.RegisterRequest;
import ru.fit.fitlyfe.services.impl.AuthenticationServiceImpl;

@RestController
@RequestMapping("/api/in")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationServiceImpl service;

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
		return ResponseEntity.ok(service.register(request));
	}

	@PostMapping("/auth")
	public ResponseEntity<AuthenticationResponse> auth(@RequestBody AuthenticationRequest request){
		return ResponseEntity.ok(service.auth(request));
	}


	@ExceptionHandler(AuthenticationBadRequestException.class)
	public ResponseEntity<ErrorResponse> badRequestAuth(AuthenticationBadRequestException exception){
		ApiError error = new ApiError(exception.getMessage(), HttpStatus.BAD_REQUEST.name());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(error));
	}

	@ExceptionHandler(RegisterBadRequestException.class)
	public ResponseEntity<ErrorResponse> badRequestRegister(RegisterBadRequestException exception){
		ApiError error = new ApiError(exception.getMessage(), HttpStatus.BAD_REQUEST.name());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(error));
	}
}
