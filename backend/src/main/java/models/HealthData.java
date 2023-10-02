package models;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.val;

@Data
public class HealthData {
	@NotNull
	private int id;

	private int heartRate;
	private float bloodSugarLevel;
	private String bloodPressure;
	@NotNull
	private final User user;

	public HealthData(User user) {
		this.user = user;
	}
}
