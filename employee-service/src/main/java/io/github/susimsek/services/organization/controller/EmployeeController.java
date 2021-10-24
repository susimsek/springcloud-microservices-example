package io.github.susimsek.services.organization.controller;

import io.github.susimsek.services.organization.exception.RecordNotFoundException;
import io.github.susimsek.services.organization.model.Employee;
import io.github.susimsek.services.organization.repository.EmployeeRepository;
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

@Tag(name = "employee", description = "the  Employee API")
@Slf4j
@RequiredArgsConstructor
@RestController
public class EmployeeController {

	private final EmployeeRepository repository;

	@Operation(summary = "Create Employee", tags = { "employee" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "successful operation",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))
					}
			),
			@ApiResponse(responseCode = "400", description = "Invalid Employee supplied", content = @Content)
	})
	@PostMapping(value = "/",
			produces = { "application/json" },
			consumes = { "application/json"})
	public ResponseEntity<Employee> add(@Parameter(description="Employee to add. Cannot null or empty.", required=true, schema=@Schema(implementation = Employee.class))
											@Valid @RequestBody Employee employee) {
		log.info("Employee add: {}", employee);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(employee));
	}

	@Operation(summary = "Find All Employees", tags = { "employee" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation",
					content = {
							@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Employee.class)))
					}
			)
	})
	@GetMapping(value = "/", produces = { "application/json"})
	public ResponseEntity<List<Employee>> findAll() {
		log.info("Employee find");
		return ResponseEntity.ok()
				.body(repository.findAll());
	}

	@Operation(summary = "Find Employee by ID", tags = { "employee" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation",
					content = {
							@Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))
					}
			),
			@ApiResponse(responseCode = "400", description = "Invalid Employee ID supplied",content = @Content),
			@ApiResponse(responseCode = "404", description = "Employee not found", content = @Content)
	})
	@GetMapping(value = "/{id}", produces = { "application/json"})
	public ResponseEntity<Employee> findById(@Parameter(description="Id of the employee to be obtained. Cannot be empty.", required=true) Long id) {
		log.info("Employee find: id={}", id);
		Employee employee = repository.findById(id).orElseThrow(() -> new RecordNotFoundException(String.format("Employee %s not found", id)));
		return ResponseEntity.ok()
				.body(employee);
	}


	@Operation(summary = "Find All Employees by Department ID", tags = { "employee" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation",
					content = {
							@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Employee.class)))
					}
			)
	})
	@GetMapping(value = "/department/{departmentId}", produces = { "application/json"})
	public ResponseEntity<List<Employee>> findByDepartment(@Parameter(description="Id of the department to be obtained. Cannot be empty.", required=true) @PathVariable("departmentId") Long departmentId) {
		log.info("Employee find: departmentId={}", departmentId);
		return ResponseEntity.ok(repository.findByDepartmentId(departmentId));
	}
}
