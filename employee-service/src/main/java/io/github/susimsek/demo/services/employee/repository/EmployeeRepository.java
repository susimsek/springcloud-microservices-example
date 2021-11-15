package io.github.susimsek.demo.services.employee.repository;

import io.github.susimsek.demo.services.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByDepartmentId(Long departmentId);

	@Query("SELECT e FROM Employee e WHERE e.departmentId = :departmentId")
	List<Employee> findAllEmployeesByDepartmentId(@Param("departmentId") Long departmentId);
}
