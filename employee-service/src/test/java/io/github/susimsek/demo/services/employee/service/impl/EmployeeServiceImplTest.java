package io.github.susimsek.demo.services.employee.service.impl;

import io.github.susimsek.demo.services.employee.exception.RecordNotFoundException;
import io.github.susimsek.demo.services.employee.model.Employee;
import io.github.susimsek.demo.services.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static io.github.susimsek.demo.services.employee.util.EmployeeUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Test
    public void whenSaveEmployee_shouldReturnEmployee() {
        Employee employeeForSave = getSampleEmployeeForSave();
        Employee employee = getSampleEmployee();

        Mockito.when(employeeRepository.save(ArgumentMatchers.refEq(employeeForSave))).thenReturn(employee);

        Employee expected = employeeService.createEmployee(employeeForSave);
        assertNotNull(expected);
        assertEquals(expected.getId(), employee.getId());
        assertEquals(expected.getName(), employee.getName());
        assertEquals(expected.getPosition(), employee.getPosition());

        verify(employeeRepository, times(1)).save(ArgumentMatchers.refEq(employeeForSave));
    }

    @Test
    public void shouldReturnAllEmployees() {
        List<Employee> employees = getSampleAllEmployees();
        Mockito.when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> expected = employeeService.getAllEmployees();

        assertNotNull(expected);
        assertEquals(expected.size(), employees.size());
        assertEquals(expected, employees);

        verify(employeeRepository, times(1)).findAll();

    }

    @Test
    public void shouldReturnAllEmployeesWithEmployees() {
        List<Employee> employees = getSampleAllEmployees();
        Mockito.when(employeeRepository.findByDepartmentId(DEFAULT_DEPARTMENT_ID)).thenReturn(employees);

        List<Employee> expected = employeeService.getAllEmployeesByDepartmentId(DEFAULT_DEPARTMENT_ID);

        assertNotNull(expected);
        assertEquals(expected.size(), employees.size());
        assertEquals(expected, employees);

        verify(employeeRepository, times(1)).findByDepartmentId(DEFAULT_DEPARTMENT_ID);
    }

    @Test
    public void whenGivenId_shouldReturnEmployee_ifEmployeeExists() {
        Employee employee = getSampleEmployee();
        Mockito.when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));

        Employee expected = employeeService.getEmployeeById(employee.getId());

        assertNotNull(expected);
        assertEquals(expected.getId(), employee.getId());
        assertEquals(expected.getName(), employee.getName());
        assertEquals(expected.getPosition(), employee.getPosition());

        verify(employeeRepository, times(1)).findById(employee.getId());

    }

    @Test
    public void whenGivenId_shouldThrowException_ifEmployeeNotExists() {
        Employee employee = getSampleEmployee();
        Mockito.when(employeeRepository.findById(employee.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(RecordNotFoundException.class, () -> {
            Employee expected = employeeService.getEmployeeById(employee.getId());
        });

        verify(employeeRepository, times(1)).findById(employee.getId());
    }
}