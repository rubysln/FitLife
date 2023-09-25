package models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class User {
	@NotNull
	private int id;

	@Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
	@NotEmpty(message = "Name should not be empty")
	private String username;

	@Size(min = 6, max = 32, message = "Password should be between 6 and 32 characters")
	private String password_hash;

	@Email
	@NotEmpty
	private String email;
	private float height;
	private float weight;
	private String date;
}
