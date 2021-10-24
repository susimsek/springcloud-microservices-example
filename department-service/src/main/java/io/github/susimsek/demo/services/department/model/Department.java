package io.github.susimsek.demo.services.department.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "employees")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_id_seq")
	@SequenceGenerator(name="department_id_seq", sequenceName = "DEPARTMENT_ID_SEQ", allocationSize = 1)
	@Schema(description = "Unique identifier of the Department.", example = "500", required = true)
	private Long id;

	@Schema(description = "Name of the Department.", example = "Devops", required = true)
	@NotBlank
	private String name;

	@Transient
	@Schema(description = "Employees of the Department.", required = true)
	private List<Employee> employees = new ArrayList<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (!(o instanceof Department))
			return false;

		Department other = (Department) o;

		return id != null && id.equals(other.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
