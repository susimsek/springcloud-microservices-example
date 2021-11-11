package io.github.susimsek.demo.services.employee.repository;

import io.github.susimsek.demo.services.employee.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static io.github.susimsek.demo.services.employee.util.EmployeeUtil.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void whenFndAllEmployeesByDepartmentId_shouldReturnEmployees() {
        Employee employeeForSave = getSampleEmployeeForSave();
        Employee employee = entityManager.persist(employeeForSave);

        List<Employee> expected = employeeRepository.findAllEmployeesByDepartmentId(employeeForSave.getDepartmentId());

        assertNotNull(expected);
        assertFalse(expected.isEmpty());

        entityManager.remove(employee);
    }
    
}
