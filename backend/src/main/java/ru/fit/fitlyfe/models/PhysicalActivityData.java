package ru.fit.fitlyfe.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "physical_Activity")
public class PhysicalActivityData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "data_id")
	private Long dataId;


	@Column(name = "steps")
	private Integer steps;


	@Column(name = "distance")
	private Float distance;


	@Column(name = "workout_time")
	private String workoutTime;


	@Column(name = "calories_burned")
	private Float caloriesBurned;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_profile_id")
	private UserProfile userProfile;

	public PhysicalActivityData(UserProfile userProfile){
		this.userProfile = userProfile;
	}
}
