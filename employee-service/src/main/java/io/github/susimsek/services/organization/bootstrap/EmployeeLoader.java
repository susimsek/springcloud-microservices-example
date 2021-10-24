package io.github.susimsek.services.organization.bootstrap;

import io.github.susimsek.services.organization.model.Employee;
import io.github.susimsek.services.organization.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeLoader implements CommandLineRunner {
    
    private final EmployeeRepository employeeRepository;
    
    @Override
    public void run(String... args) {
        employeeRepository.save(
                Employee.builder().departmentId(1L).name("John Smith").age(34).position("Analyst").build());
        employeeRepository.save(
                Employee.builder().departmentId(1L).name("Darren Hamilton").age(37).position("Manager").build());
        employeeRepository.save(
                Employee.builder().departmentId(2L).name("Anna London").age(39).position("Analyst").build());
        employeeRepository.save(
                Employee.builder().departmentId(2L).name("Patrick Dempsey").age(27).position("Developer").build());
        employeeRepository.save(
                Employee.builder().departmentId(3L).name("Kevin Price").age(38).position("Developer").build());
        employeeRepository.save(
                Employee.builder().departmentId(3L).name("Ian Scott").age(34).position("Developer").build());
        employeeRepository.save(
                Employee.builder().departmentId(23L).name("Andrew Campton").age(34).position("Manager").build());
        employeeRepository.save(
                Employee.builder().departmentId(4L).name("Steve Franklin").age(25).position("Manager").build());
        employeeRepository.save(
                Employee.builder().departmentId(4L).name("Elisabeth Smith").age(30).position("Developer").build());
    }
}
