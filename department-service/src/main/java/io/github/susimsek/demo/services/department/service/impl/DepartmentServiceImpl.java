package io.github.susimsek.demo.services.department.service.impl;

import io.github.susimsek.demo.services.department.client.EmployeeClient;
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
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Department %s not found", id)));
    }

    @Override
    public List<Department> getAllDepartmentsWithEmployees() {
        List<Department> departments = departmentRepository.findAll();
        departments.forEach(d -> d.setEmployees(employeeClient.findByDepartment(d.getId())));
        return departments;
    }
}
