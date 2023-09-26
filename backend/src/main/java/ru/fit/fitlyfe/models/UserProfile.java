package ru.fit.fitlyfe.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class UserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
	@NotEmpty(message = "Name should not be empty")
	private String username;

	@Size(min = 6, max = 32, message = "Password should be between 6 and 32 characters")
	private String password_hash;

	@Email
	@NotEmpty
	private String email;

	@NotNull
	private float height;

	@NotNull
	private float weight;

	@NotNull
	private String date;
}

