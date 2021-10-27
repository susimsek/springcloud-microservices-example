package io.github.susimsek.demo.services.employee.service;

import io.github.susimsek.demo.services.employee.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee departmentForSave);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    List<Employee> getAllEmployeesByDepartmentId(Long departmentId);
}
