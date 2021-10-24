package io.github.susimsek.services.organization.repository;

import io.github.susimsek.services.organization.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByDepartmentId(Long departmentId);
}
