package io.github.susimsek.services.organization.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_seq")
	@SequenceGenerator(name="employee_id_seq", sequenceName = "EMPLOYEE_ID_SEQ", allocationSize = 1)
	@Schema(description = "Unique identifier of the Employee.", example = "500", required = true)
	private Long id;

	@Schema(description = "Unique identifier of the Department.", example = "1", required = true)
	@NotNull
	private Long departmentId;

	@Schema(description = "Name of the Employee.", example = "Harry Pother", required = true)
	@NotBlank
	private String name;

	@Schema(description = "Age of the Employee.", example = "18", required = true)
	@Min(1)
	private int age;

	@Schema(description = "Position of the Employee.", example = "Developer", required = true)
	@NotBlank
	private String position;

}
