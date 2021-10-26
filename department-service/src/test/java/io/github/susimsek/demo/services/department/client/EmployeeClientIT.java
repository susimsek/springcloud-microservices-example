package io.github.susimsek.demo.services.department.client;

import io.github.susimsek.demo.services.department.DepartmentApplication;
import io.github.susimsek.demo.services.department.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = DepartmentApplication.class)
@AutoConfigureWireMock(port = 0, stubs = "classpath:/stubs")
public class EmployeeClientIT {

    @Autowired
    EmployeeClient employeeClient;


    @Test
    public void getAllEmployeesByDepartmentId_whenGetMethod() {
        List<Employee> employees = employeeClient.findByDepartment(1L);
        assertFalse(employees.isEmpty());
    }
}
