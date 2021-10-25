package io.github.susimsek.demo.services.department.repository;

import io.github.susimsek.demo.services.department.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByOrganizationId(Long organizationId);
}
