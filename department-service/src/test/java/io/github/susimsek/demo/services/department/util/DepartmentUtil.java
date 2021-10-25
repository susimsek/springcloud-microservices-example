package io.github.susimsek.demo.services.department.util;

import io.github.susimsek.demo.services.department.model.Department;
import io.github.susimsek.demo.services.department.model.Employee;

import java.util.List;

public class DepartmentUtil {

    public static final Long DEFAULT_ORGANIZATION_ID = 1L;

    public static Department getSampleDepartmentForSave(){
        return Department.builder()
                .organizationId(DEFAULT_ORGANIZATION_ID)
                .name("Development")
                .build();
    }

    public static Department getSampleDepartment(){
        return Department.builder()
                .id(1L)
                .organizationId(DEFAULT_ORGANIZATION_ID)
                .name("Development")
                .build();
    }

    public static List<Department> getSampleAllDepartments(){
        Department department1 = Department.builder()
                .id(1L).organizationId(DEFAULT_ORGANIZATION_ID).name("Development").build();
        Department department2 = Department.builder()
                .id(2L).organizationId(DEFAULT_ORGANIZATION_ID).name("Operations").build();
        return List.of(department1, department2);
    }

    public static List<Department> getSampleAllDepartmentsWithEmployees(){
        List<Employee> employees = EmployeeUtil.getSampleAllEmployees();
        Department department1 = Department.builder()
                .id(1L).name("Development").organizationId(DEFAULT_ORGANIZATION_ID).employees(employees).build();
        Department department2 = Department.builder()
                .id(2L).name("Operations").organizationId(DEFAULT_ORGANIZATION_ID).employees(employees).build();
        return List.of(department1, department2);
    }
}
