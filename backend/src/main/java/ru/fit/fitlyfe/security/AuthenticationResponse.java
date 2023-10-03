package ru.fit.fitlyfe.security;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
public class AuthenticationResponse {

	private String token;
}
