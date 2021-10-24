package io.github.susimsek.demo.services.department.controller;

import io.github.susimsek.demo.services.department.DepartmentApplication;
import io.github.susimsek.demo.services.department.model.Department;
import io.github.susimsek.demo.services.department.model.Employee;
import io.github.susimsek.demo.services.department.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static io.github.susimsek.demo.services.department.client.EmployeeClientStub.stubFindEmployeesByDepartmentId;
import static io.github.susimsek.demo.services.department.util.DepartmentUtil.getSampleDepartmentForSave;
import static io.github.susimsek.demo.services.department.util.EmployeeUtil.getSampleAllEmployees;
import static io.github.susimsek.demo.services.department.util.JsonUtil.toJson;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = DepartmentApplication.class)
@AutoConfigureMockMvc
@AutoConfigureWireMock(port = 0)
public class DepartmentControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    public void createDepartment() throws Exception {
        Department departmentForSave = getSampleDepartmentForSave();

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(toJson(departmentForSave));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(departmentForSave.getName()));

    }

    @Test
    public void getAllDepartments() throws Exception {
        Department department = departmentRepository.save(getSampleDepartmentForSave());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[*].id").value(hasItem(department.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(department.getName())));
    }

    @Test
    public void getDepartmentById() throws Exception {
        Department department = departmentRepository.save(getSampleDepartmentForSave());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/{id}",department.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id").value(department.getId().intValue()))
                .andExpect(jsonPath("$.name").value(department.getName()));
    }

    @Test
    public void getAllDepartmentsWithEmployees() throws Exception {
        Department department = departmentRepository.save(getSampleDepartmentForSave());
        stubFindEmployeesByDepartmentId();
        List<Employee> employees = getSampleAllEmployees();

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/with-employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.[*].id").value(hasItem(department.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(department.getName())))
                .andExpect(jsonPath("$.[*].employees").isNotEmpty())
                .andExpect(jsonPath("$.[*].employees.[*].name").value(hasItem(employees.get(0).getName())));
    }

    @Test
    void getNonExistingDepartment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/{id}",Long.MAX_VALUE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void createInvalidDepartment() throws Exception {
        Department departmentForSave = getSampleDepartmentForSave();
        departmentForSave.setName(null);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(toJson(departmentForSave));

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest());
    }


}
