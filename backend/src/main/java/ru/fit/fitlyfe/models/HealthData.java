package ru.fit.fitlyfe.models;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class HealthData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 0, message = "Are you dead?")
	private int heartRate;
	@NotNull
	private float bloodSugarLevel;
	@NotNull
	private String bloodPressure;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private UserProfile user;

	public HealthData(UserProfile user) {
		this.user = user;
	}
}