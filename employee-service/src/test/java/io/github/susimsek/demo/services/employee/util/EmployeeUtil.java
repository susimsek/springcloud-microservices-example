package io.github.susimsek.demo.services.employee.util;

import io.github.susimsek.demo.services.employee.model.Employee;

import java.util.List;

public class EmployeeUtil {

    public static final Long DEFAULT_EMPLOYEE_ID = 1L;
    public static final Long DEFAULT_DEPARTMENT_ID = 1L;

    public static Employee getSampleEmployeeForSave(){
        return Employee.builder()
                .departmentId(DEFAULT_DEPARTMENT_ID)
                .name("John Smith")
                .age(34)
                .position("Analyst")
                .build();
    }

    public static Employee getSampleEmployee(){
        return Employee.builder()
                .id(DEFAULT_EMPLOYEE_ID)
                .departmentId(DEFAULT_DEPARTMENT_ID)
                .name("John Smith")
                .age(34)
                .position("Analyst")
                .build();
    }

    public static List<Employee> getSampleAllEmployees(){
        Employee employee1 = Employee.builder()
                .id(DEFAULT_EMPLOYEE_ID)
                .departmentId(DEFAULT_DEPARTMENT_ID)
                .name("John Smith").age(34).position("Analyst").build();
        Employee employee2 = Employee.builder()
                .id(2L)
                .departmentId(DEFAULT_DEPARTMENT_ID)
                .name("Darren Hamilton").age(37).position("Manager").build();
        return List.of(employee1, employee2);
    }
}
