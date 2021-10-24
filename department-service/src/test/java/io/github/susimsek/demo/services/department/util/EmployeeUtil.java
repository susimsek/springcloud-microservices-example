package io.github.susimsek.demo.services.department.util;

import io.github.susimsek.demo.services.department.model.Employee;

import java.util.List;

public class EmployeeUtil {

    public static List<Employee> getSampleAllEmployees(){
        Employee employee1 = Employee.builder()
                .id(1L)
                .name("John Smith").age(34).position("Analyst").build();
        Employee employee2 = Employee.builder()
                .id(2L)
                .name("Darren Hamilton").age(37).position("Manager").build();
        return List.of(employee1, employee2);
    }
}
