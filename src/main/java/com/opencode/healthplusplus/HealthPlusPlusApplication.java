package com.opencode.healthplusplus;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition
@SpringBootApplication
@EnableJpaAuditing
public class HealthPlusPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthPlusPlusApplication.class, args);
    }

}
