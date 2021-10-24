package io.github.susimsek.demo.services.department.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Employee {

	@Schema(description = "Unique identifier of the Employee.", example = "500", required = true)
	private Long id;

	@Schema(description = "Name of the Employee.", example = "Harry Pother", required = true)
	private String name;

	@Schema(description = "Age of the Employee.", example = "24", required = true)
	private int age;

	@Schema(description = "Position of the Employee.", example = "Developer", required = true)
	private String position;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (!(o instanceof Employee))
			return false;

		Employee other = (Employee) o;

		return id != null && id.equals(other.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
