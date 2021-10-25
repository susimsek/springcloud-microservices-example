package io.github.susimsek.demo.services.department.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.susimsek.demo.services.department.config.OrganizationClientConfig;
import io.github.susimsek.demo.services.department.model.Organization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;


import static io.github.susimsek.demo.services.department.client.OrganizationClientStub.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = OrganizationClientConfig.class)
@AutoConfigureWireMock(port = 0)
public class OrganizationClientIT {


    @Autowired
    OrganizationClient organizationClient;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        stubFindOrganizationById();
    }

    @Test
    public void getOrganizationById_whenGetMethod() {
        Organization organization = organizationClient.findByOrganization(1L);
        assertNotNull(organization);
    }
}
