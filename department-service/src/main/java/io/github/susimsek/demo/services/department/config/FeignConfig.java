package io.github.susimsek.demo.services.department.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableAutoConfiguration
@EnableFeignClients(basePackages = "io.github.susimsek.demo.services.department.client.feign")
public class FeignConfig {
}
