package io.github.susimsek.services.gateway.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(ApiDocProperties.class)
public class ApiDocConfig {

    @Bean
    public OpenAPI customOpenAPI(ApiDocProperties apiDocProperties) {
        return new OpenAPI()
                .components(new Components())
                .info(metaData(apiDocProperties));
    }


    private Info metaData(ApiDocProperties apiDocProperties) {
        return new Info()
                .title(apiDocProperties.getTitle())
                .description(apiDocProperties.getDescription())
                .version(apiDocProperties.getVersion())
                .termsOfService(apiDocProperties.getTermsOfServiceUrl())
                .license(new License()
                        .name(apiDocProperties.getLicense())
                        .url(apiDocProperties.getLicenseUrl()));
    }

    @Bean
    @Lazy(false)
    public List<GroupedOpenApi> apis(SwaggerUiConfigParameters swaggerUiConfigParameters, RouteDefinitionLocator locator) {
        List<GroupedOpenApi> groups = new ArrayList<>();
        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
        for (RouteDefinition definition : definitions) {
            System.out.println("id: " + definition.getId() +  "  " + definition.getUri().toString());
        }
        definitions.stream()
                .filter(routeDefinition -> routeDefinition.getId().matches(".*-service"))
                .forEach(routeDefinition -> {
                    String name = routeDefinition.getId().replaceAll("-service", "");
                    swaggerUiConfigParameters.addGroup(name);
                    GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build();
                });
        return groups;
    }
}