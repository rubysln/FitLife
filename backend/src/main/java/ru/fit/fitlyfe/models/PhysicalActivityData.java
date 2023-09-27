package ru.fit.fitlyfe.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PhysicalActivityData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dataId;

	@NotNull
	private int steps;

	@NotNull
	private float distance;

	@NotNull
	private String workoutTime;

	@NotNull
	private float caloriesBurned;

	@NotNull
	@NotEmpty
	@ManyToOne(fetch = FetchType.LAZY)
	private UserProfile user;

	public PhysicalActivityData(UserProfile userProfile){
		this.user = userProfile;
	}
}
