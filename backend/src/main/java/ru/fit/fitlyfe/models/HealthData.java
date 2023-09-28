package ru.fit.fitlyfe.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "health_data")
public class HealthData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "heart_rate")
	private int heartRate;

	@NotNull
	@Column(name = "blood_sugar_lvl")
	private float bloodSugarLevel;

	@NotNull
	@Column(name = "blood_pressure")
	private String bloodPressure;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_profile_id")
	private UserProfile userProfile;
}