package io.github.susimsek.demo.services.department.service.impl;

import io.github.susimsek.demo.services.department.client.EmployeeClient;
import io.github.susimsek.demo.services.department.client.OrganizationClient;
import io.github.susimsek.demo.services.department.exception.RecordNotFoundException;
import io.github.susimsek.demo.services.department.model.Department;
import io.github.susimsek.demo.services.department.repository.DepartmentRepository;
import io.github.susimsek.demo.services.department.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeClient employeeClient;
    private final OrganizationClient organizationClient;

    @Override
    public Department createDepartment(Department departmentForSave) {
        return departmentRepository.save(departmentForSave);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Department %s not found", id)));
        return department;
    }

    @Override
    public List<Department> getAllDepartmentsWithEmployees(Long organizationId) {
        List<Department> departments = departmentRepository.findByOrganizationId(organizationId);
        departments.forEach(d -> d.setEmployees(employeeClient.findByDepartment(d.getId())));
        return departments;
    }

    @Override
    public List<Department> getAllDepartmentsByOrganizationId(Long organizationId) {
        List<Department> departments = departmentRepository.findByOrganizationId(organizationId);
        departments.forEach(d -> d.setOrganization(organizationClient.findByOrganization(d.getOrganizationId())));
        return departments;
    }
}
