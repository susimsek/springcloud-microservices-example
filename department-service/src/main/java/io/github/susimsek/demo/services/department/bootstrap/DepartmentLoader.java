package io.github.susimsek.demo.services.department.bootstrap;

import io.github.susimsek.demo.services.department.model.Department;
import io.github.susimsek.demo.services.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartmentLoader implements CommandLineRunner {
    
    private final DepartmentRepository departmentRepository;
    
    @Override
    public void run(String... args) {
        departmentRepository.save(
                Department.builder().name("Development").build());
        departmentRepository.save(
                Department.builder().name("Operations").build());
        departmentRepository.save(
                Department.builder().name("Development").build());
        departmentRepository.save(
                Department.builder().name("Operations").build());
    }
}
