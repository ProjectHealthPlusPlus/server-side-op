package com.opencode.healthplusplus.health.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("healthMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public ClinicLocationMapper clinicLocationMapper() {
        return new ClinicLocationMapper();
    }
}
