package models;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PhysicalActivityData {
	@NotNull
	private int dataId;

	private int steps;
	private float distance;
	private String workoutTime;
	private float caloriesBurned;
	@NotNull
	private final User user;

	public PhysicalActivityData(User user) {
		this.user = user;
	}
}
