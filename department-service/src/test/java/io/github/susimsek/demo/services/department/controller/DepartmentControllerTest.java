package io.github.susimsek.demo.services.department.controller;

import io.github.susimsek.demo.services.department.exception.RecordNotFoundException;
import io.github.susimsek.demo.services.department.model.Department;
import io.github.susimsek.demo.services.department.service.DepartmentService;
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

import static io.github.susimsek.demo.services.department.util.DepartmentUtil.*;
import static io.github.susimsek.demo.services.department.util.JsonUtil.toJson;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DepartmentService departmentService;

    @Test
    public void createDepartment_whenPostMethod() throws Exception {

        Department departmentForSave = getSampleDepartmentForSave();
        Department department = getSampleDepartment();

        Mockito.when(departmentService.createDepartment(ArgumentMatchers.refEq(departmentForSave))).thenReturn(department);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(toJson(departmentForSave));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(departmentForSave.getName()));

        verify(departmentService, times(1)).createDepartment(ArgumentMatchers.refEq(departmentForSave));
    }

    @Test
    public void getAllDepartments_whenGetMethod() throws Exception {
        List<Department> departments = getSampleAllDepartments();

        Mockito.when(departmentService.getAllDepartments()).thenReturn(getSampleAllDepartments());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(departments.size())))
                .andExpect(jsonPath("$[0].name").value(departments.get(0).getName()));

        verify(departmentService, times(1)).getAllDepartments();
    }

    @Test
    public void getDepartmentById_whenGetMethod() throws Exception {
        Department department = getSampleDepartment();
        Mockito.when(departmentService.getDepartmentById(department.getId())).thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/{id}",department.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value(department.getName()));

        verify(departmentService, times(1)).getDepartmentById(department.getId());
    }

    @Test
    public void getAllDepartmentsWithEmployees_whenGetMethod() throws Exception {
        List<Department> departments = getSampleAllDepartmentsWithEmployees();

        Mockito.when(departmentService.getAllDepartmentsWithEmployees(DEFAULT_ORGANIZATION_ID)).thenReturn(departments);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/organization/{organizationId}/with-employees",DEFAULT_ORGANIZATION_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(departments.size())))
                .andExpect(jsonPath("$[0].name").value(departments.get(0).getName()))
                .andExpect(jsonPath("$[0].employees", hasSize(departments.get(0).getEmployees().size())))
                .andExpect(jsonPath("$[0].employees[0].name").value(departments.get(0).getEmployees().get(0).getName()));

        verify(departmentService, times(1)).getAllDepartmentsWithEmployees(1L);
    }

    @Test
    public void shouldThrowException_ifDepartmentNotExists() throws Exception {
        Department department = getSampleDepartment();
        Mockito.when(departmentService.getDepartmentById(department.getId())).thenThrow(RecordNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/{id}",department.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RecordNotFoundException));

        verify(departmentService, times(1)).getDepartmentById(department.getId());
    }

    @Test
    public void shouldThrowException_ifDepartmentInvalid() throws Exception {
        Department departmentForSave = getSampleDepartmentForSave();
        departmentForSave.setName(null);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(toJson(departmentForSave));

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));

        verify(departmentService, times(0)).createDepartment(getSampleDepartmentForSave());
    }
}