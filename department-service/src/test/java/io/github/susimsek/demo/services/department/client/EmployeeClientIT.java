package io.github.susimsek.demo.services.department.client;

import io.github.susimsek.demo.services.department.DepartmentApplication;
import io.github.susimsek.demo.services.department.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import java.io.IOException;
import java.util.List;

import static io.github.susimsek.demo.services.department.client.EmployeeClientStub.stubFindEmployeesByDepartmentId;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = DepartmentApplication.class)
@AutoConfigureWireMock(port = 0)
public class EmployeeClientIT {

    @Autowired
    EmployeeClient employeeClient;

    @BeforeEach
    void setUp() throws IOException {
        stubFindEmployeesByDepartmentId();
    }

    @Test
    public void getAllEmployeesByDepartmentId_whenGetMethod() {
        List<Employee> employees = employeeClient.findByDepartment(1L);
        assertFalse(employees.isEmpty());
    }
}
