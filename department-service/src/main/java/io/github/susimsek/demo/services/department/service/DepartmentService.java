package io.github.susimsek.demo.services.department.service;

import io.github.susimsek.demo.services.department.model.Department;

import java.util.List;

public interface DepartmentService {

    Department createDepartment(Department departmentForSave);

    List<Department> getAllDepartments();

    Department getDepartmentById(Long id);

    List<Department> getAllDepartmentsWithEmployees();
}
