package io.github.susimsek.demo.services.employee.service.impl;
import io.github.susimsek.demo.services.employee.exception.RecordNotFoundException;
import io.github.susimsek.demo.services.employee.model.Employee;
import io.github.susimsek.demo.services.employee.repository.EmployeeRepository;
import io.github.susimsek.demo.services.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee departmentForSave) {
        return employeeRepository.save(departmentForSave);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Employee %s not found", id)));
    }

    @Override
    public List<Employee> getAllEmployeesByDepartmentId(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }
}
