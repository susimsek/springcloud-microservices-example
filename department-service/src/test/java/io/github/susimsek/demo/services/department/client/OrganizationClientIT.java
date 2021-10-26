package io.github.susimsek.demo.services.department.client;

import io.github.susimsek.demo.services.department.config.OrganizationClientConfig;
import io.github.susimsek.demo.services.department.model.Organization;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;


import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = OrganizationClientConfig.class)
@AutoConfigureWireMock(port = 0, stubs = "classpath:/stubs")
public class OrganizationClientIT {


    @Autowired
    OrganizationClient organizationClient;


    @Test
    public void getOrganizationById_whenGetMethod() {
        Organization organization = organizationClient.findByOrganization(1L);
        assertNotNull(organization);
    }
}
