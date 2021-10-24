package io.github.susimsek.demo.services.department.util;

import io.github.susimsek.demo.services.department.model.Department;
import io.github.susimsek.demo.services.department.model.Employee;

import java.util.List;

public class DepartmentUtil {

    public static Department getSampleDepartmentForSave(){
        return Department.builder()
                .name("Development")
                .build();
    }

    public static Department getSampleDepartment(){
        return Department.builder()
                .id(1L)
                .name("Development")
                .build();
    }

    public static List<Department> getSampleAllDepartments(){
        Department department1 = Department.builder()
                .id(1L).name("Development").build();
        Department department2 = Department.builder()
                .id(2L).name("Operations").build();
        return List.of(department1, department2);
    }

    public static List<Department> getSampleAllDepartmentsWithEmployees(){
        List<Employee> employees = EmployeeUtil.getSampleAllEmployees();
        Department department1 = Department.builder()
                .id(1L).name("Development").employees(employees).build();
        Department department2 = Department.builder()
                .id(2L).name("Operations").employees(employees).build();
        return List.of(department1, department2);
    }
}
