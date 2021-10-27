package io.github.susimsek.demo.services.organization.bootstrap;


import io.github.susimsek.demo.services.organization.model.Organization;
import io.github.susimsek.demo.services.organization.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrganizationLoader implements CommandLineRunner {
    
    private final OrganizationRepository organizationRepository;
    
    @Override
    public void run(String... args) {
        organizationRepository.save(
                Organization.builder().name("Microsoft").address("Redmond, Washington, USA").build());
        organizationRepository.save(
                Organization.builder().name("Oracle").address("Redwood City, California, USA").build());
    }
}
