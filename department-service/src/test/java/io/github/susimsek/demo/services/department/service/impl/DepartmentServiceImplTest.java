package io.github.susimsek.demo.services.department.service.impl;

import io.github.susimsek.demo.services.department.client.feign.EmployeeClient;
import io.github.susimsek.demo.services.department.exception.RecordNotFoundException;
import io.github.susimsek.demo.services.department.model.Department;
import io.github.susimsek.demo.services.department.model.Employee;
import io.github.susimsek.demo.services.department.repository.DepartmentRepository;
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

import static io.github.susimsek.demo.services.department.util.DepartmentUtil.*;
import static io.github.susimsek.demo.services.department.util.EmployeeUtil.getSampleAllEmployees;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    @Mock
    DepartmentRepository departmentRepository;

    @Mock
    EmployeeClient employeeClient;

    @InjectMocks
    DepartmentServiceImpl departmentService;

    @Test
    public void whenSaveDepartment_shouldReturnDepartment() {
        Department departmentForSave = getSampleDepartmentForSave();
        Department department = getSampleDepartment();

        Mockito.when(departmentRepository.save(ArgumentMatchers.refEq(departmentForSave))).thenReturn(department);

        Department expected = departmentService.createDepartment(departmentForSave);
        assertNotNull(expected);
        assertEquals(expected.getId(), department.getId());
        assertEquals(expected.getName(), department.getName());

        verify(departmentRepository, times(1)).save(ArgumentMatchers.refEq(departmentForSave));
    }

    @Test
    public void shouldReturnAllDepartments() {
        List<Department> departments = getSampleAllDepartments();
        Mockito.when(departmentRepository.findAll()).thenReturn(departments);

        List<Department> expected = departmentService.getAllDepartments();

        assertNotNull(expected);
        assertEquals(expected.size(), departments.size());
        assertEquals(expected, departments);

        verify(departmentRepository, times(1)).findAll();

    }

    @Test
    public void shouldReturnAllDepartmentsWithEmployees() {
        List<Department> departments = getSampleAllDepartments();
        List<Employee> employees = getSampleAllEmployees();
        Mockito.when(departmentRepository.findByOrganizationId(DEFAULT_ORGANIZATION_ID)).thenReturn(departments);
        Mockito.when(employeeClient.findByDepartment(anyLong())).thenReturn(getSampleAllEmployees());

        List<Department> expected = departmentService.getAllDepartmentsWithEmployees(DEFAULT_ORGANIZATION_ID);

        assertNotNull(expected);
        assertEquals(expected.size(), departments.size());
        assertEquals(expected.get(0).getEmployees().size(), employees.size());
        assertEquals(expected.get(0).getEmployees(), employees);

        verify(departmentRepository, times(1)).findByOrganizationId(DEFAULT_ORGANIZATION_ID);
        verify(employeeClient, times(departments.size())).findByDepartment(anyLong());
    }

    @Test
    public void whenGivenId_shouldReturnDepartment_ifDepartmentExists() {
        Department department = getSampleDepartment();
        Mockito.when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));

        Department expected = departmentService.getDepartmentById(department.getId());

        assertNotNull(expected);
        assertEquals(expected.getId(), department.getId());
        assertEquals(expected.getName(), department.getName());

        verify(departmentRepository, times(1)).findById(department.getId());

    }

    @Test
    public void whenGivenId_shouldThrowException_ifDepartmentNotExists() {
        Department department = getSampleDepartment();
        Mockito.when(departmentRepository.findById(department.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(RecordNotFoundException.class, () -> {
            Department expected = departmentService.getDepartmentById(department.getId());
        });

        verify(departmentRepository, times(1)).findById(department.getId());
    }
}