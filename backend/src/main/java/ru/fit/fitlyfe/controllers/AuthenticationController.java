package ru.fit.fitlyfe.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
	public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request){
		return ResponseEntity.ok(service.auth(request));
	}
}
