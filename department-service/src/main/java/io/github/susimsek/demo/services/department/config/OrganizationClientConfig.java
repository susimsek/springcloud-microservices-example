package io.github.susimsek.demo.services.department.config;

import io.github.susimsek.demo.services.department.client.OrganizationClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration(proxyBeanMethods = false)
public class OrganizationClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("io.github.susimsek.demo.services.department.model");
        return marshaller;
    }

    @Bean
    public OrganizationClient countryClient(Jaxb2Marshaller marshaller, Environment env) {
        OrganizationClient client = new OrganizationClient();
        client.setDefaultUri(String.format("%s/ws",env.getProperty("organization.service.url")));
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}