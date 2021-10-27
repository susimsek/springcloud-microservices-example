package io.github.susimsek.demo.services.employee.controller;


import io.github.susimsek.demo.services.employee.EmployeeApplication;
import io.github.susimsek.demo.services.employee.model.Employee;
import io.github.susimsek.demo.services.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.List;

import static io.github.susimsek.demo.services.employee.util.EmployeeUtil.*;
import static io.github.susimsek.demo.services.employee.util.JsonUtil.*;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = EmployeeApplication.class)
@AutoConfigureMockMvc
public class EmployeeControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void createEmployee() throws Exception {
        Employee employeeForSave = getSampleEmployeeForSave();

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(toJson(employeeForSave));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(employeeForSave.getName()))
                .andExpect(jsonPath("$.position").value(employeeForSave.getPosition()));;

    }

    @Test
    public void getAllEmployees() throws Exception {
        Employee employee = employeeRepository.save(getSampleEmployeeForSave());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[*].id").value(hasItem(employee.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(employee.getName())))
                .andExpect(jsonPath("$.[*].position").value(hasItem(employee.getPosition())));
    }

    @Test
    public void getEmployeeById() throws Exception {
        Employee employee = employeeRepository.save(getSampleEmployeeForSave());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/{id}",employee.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(employee.getId().intValue()))
                .andExpect(jsonPath("$.name").value(employee.getName()))
                .andExpect(jsonPath("$.position").value(employee.getPosition()));
    }

    @Test
    public void getAllEmployeesByDepartmentId() throws Exception {
        Employee employee = employeeRepository.save(getSampleEmployeeForSave());
        List<Employee> employees = getSampleAllEmployees();

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/department/{departmentId}",DEFAULT_DEPARTMENT_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[*].id").value(hasItem(employee.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(employee.getName())))
                .andExpect(jsonPath("$.[*].position").value(hasItem(employee.getPosition())));
    }

    @Test
    public void getNonExistingEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/{id}",Long.MAX_VALUE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createInvalidEmployee() throws Exception {
        Employee employeeForSave = getSampleEmployeeForSave();
        employeeForSave.setName(null);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(toJson(employeeForSave));

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest());
    }


}
