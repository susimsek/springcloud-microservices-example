package io.github.susimsek.demo.services.department.controller;

import io.github.susimsek.demo.services.department.model.Department;
import io.github.susimsek.demo.services.department.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "department", description = "the  Department API")
@Slf4j
@RequiredArgsConstructor
@RestController
public class DepartmentController {
	
	private final DepartmentService departmentService;


	@Operation(summary = "Create Department", tags = { "department" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "successful operation",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Department.class))
					}
			),
			@ApiResponse(responseCode = "400", description = "Invalid Department supplied", content = @Content)
	})
	@PostMapping(value = "/",
			produces = { "application/json" },
			consumes = { "application/json"})
	public ResponseEntity<Department> add(@Parameter(description="Department to add. Cannot null or empty.", required=true, schema=@Schema(implementation = Department.class))
											  @Valid @RequestBody Department department) {
		log.info("Department add: {}", department);
		department = departmentService.createDepartment(department);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(department);
	}

	@Operation(summary = "Find All Departments", tags = { "department" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation",
					content = {
							@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Department.class)))
					}
			)
	})
	@GetMapping(value = "/",
			produces = { "application/json"})
	public ResponseEntity<List<Department>> findAll() {
		log.info("Department findAll");
		List<Department> departments =  departmentService.getAllDepartments();
		return ResponseEntity.ok()
				.body(departments);
	}

	@Operation(summary = "Find Department by ID", tags = { "department" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Department.class))
					}
			),
			@ApiResponse(responseCode = "400", description = "Invalid Department ID supplied",content = @Content),
			@ApiResponse(responseCode = "404", description = "Department not found", content = @Content)
	})
	@GetMapping(value = "/{id}", produces = { "application/json"})
	public ResponseEntity<Department> findById(@PathVariable("id") Long id) {
		log.info("Department find: id={}", id);
		Department department = departmentService.getDepartmentById(id);
		return ResponseEntity.ok()
				.body(department);
	}

	@Operation(summary = "Find All Department by Organization ID", tags = { "employee" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation",
					content = {
							@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Department.class)))
					}
			)
	})
	@GetMapping("/organization/{organizationId}")
	public List<Department> findByOrganization(@PathVariable("organizationId") Long organizationId) {
		log.info("Department find: organizationId={}", organizationId);
		return departmentService.getAllDepartmentsByOrganizationId(organizationId);
	}

	@Operation(summary = "Find All Department with Employees", tags = { "employee" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation",
					content = {
							@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Department.class)))
					}
			)
	})
	@GetMapping(value = "/organization/{organizationId}/with-employees", produces = { "application/json"})
	public ResponseEntity<List<Department>> findAllWithEmployees(@PathVariable("organizationId") Long organizationId) {
		log.info("Department findAllWithEmployees");
		List<Department> departments = departmentService.getAllDepartmentsWithEmployees(organizationId);
		return ResponseEntity.ok(departments);
	}
	
}
