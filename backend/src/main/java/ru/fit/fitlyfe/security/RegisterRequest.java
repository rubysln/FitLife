package ru.fit.fitlyfe.security;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterRequest {

	private String username;
	private String email;
	@Size(min = 6, max = 32, message = "Password should be between 6 and 32 characters")
	private String password;
}
