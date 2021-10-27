package io.github.susimsek.demo.services.employee.controller;


import io.github.susimsek.demo.services.employee.exception.RecordNotFoundException;
import io.github.susimsek.demo.services.employee.model.Employee;
import io.github.susimsek.demo.services.employee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;


import static io.github.susimsek.demo.services.employee.util.EmployeeUtil.*;
import static io.github.susimsek.demo.services.employee.util.JsonUtil.*;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmployeeService employeeService;

    @Test
    public void createEmployee_whenPostMethod() throws Exception {

        Employee employeeForSave = getSampleEmployeeForSave();
        Employee employee = getSampleEmployee();

        Mockito.when(employeeService.createEmployee(ArgumentMatchers.refEq(employeeForSave))).thenReturn(employee);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(toJson(employeeForSave));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(employeeForSave.getName()))
                .andExpect(jsonPath("$.position").value(employeeForSave.getPosition()));

        verify(employeeService, times(1)).createEmployee(ArgumentMatchers.refEq(employeeForSave));
    }

    @Test
    public void getAllEmployees_whenGetMethod() throws Exception {
        List<Employee> employees = getSampleAllEmployees();

        Mockito.when(employeeService.getAllEmployees()).thenReturn(getSampleAllEmployees());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(employees.size())))
                .andExpect(jsonPath("$[0].name").value(employees.get(0).getName()))
                .andExpect(jsonPath("$[0].position").value(employees.get(0).getPosition()));

        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    public void getEmployeeById_whenGetMethod() throws Exception {
        Employee employee = getSampleEmployee();
        Mockito.when(employeeService.getEmployeeById(employee.getId())).thenReturn(employee);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/{id}",employee.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(employee.getName()))
                .andExpect(jsonPath("$.position").value(employee.getPosition()));

        verify(employeeService, times(1)).getEmployeeById(employee.getId());
    }

    @Test
    public void getAllEmployeesByDepartmentId_whenGetMethod() throws Exception {
        List<Employee> employees = getSampleAllEmployees();

        Mockito.when(employeeService.getAllEmployeesByDepartmentId(DEFAULT_DEPARTMENT_ID)).thenReturn(employees);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/department/{departmentId}",DEFAULT_DEPARTMENT_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(employees.size())))
                .andExpect(jsonPath("$[0].name").value(employees.get(0).getName()))
                .andExpect(jsonPath("$[0].departmentId").value(employees.get(0).getDepartmentId()));

        verify(employeeService, times(1)).getAllEmployeesByDepartmentId(DEFAULT_DEPARTMENT_ID);
    }

    @Test
    public void shouldThrowException_ifEmployeeNotExists() throws Exception {
        Employee employee = getSampleEmployee();
        Mockito.when(employeeService.getEmployeeById(employee.getId())).thenThrow(RecordNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/{id}",employee.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RecordNotFoundException));

        verify(employeeService, times(1)).getEmployeeById(employee.getId());
    }

    @Test
    public void shouldThrowException_ifEmployeeInvalid() throws Exception {
        Employee employeeForSave = getSampleEmployeeForSave();
        employeeForSave.setName(null);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(toJson(employeeForSave));

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));

        verify(employeeService, times(0)).createEmployee(getSampleEmployeeForSave());
    }
}