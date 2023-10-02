package ru.fit.fitlyfe.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fit.fitlyfe.security.AuthenticationRequest;
import ru.fit.fitlyfe.security.AuthenticationResponse;
import ru.fit.fitlyfe.security.RegisterRequest;
import ru.fit.fitlyfe.services.impl.AuthenticationServiceImpl;

@RestController
@CrossOrigin(origins = "*", maxAge = 4800)
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
}
