package io.github.susimsek.services.employee.repository;

import io.github.susimsek.services.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByDepartmentId(Long departmentId);
}
