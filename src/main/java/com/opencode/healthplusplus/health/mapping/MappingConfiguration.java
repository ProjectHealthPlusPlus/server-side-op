package com.opencode.healthplusplus.health.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("healthMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public MedicalHistoryMapper medicalHistoryMapper() {
        return new MedicalHistoryMapper();
    }

    @Bean
    public DiagnosticMapper diagnosticMapper() {
        return new DiagnosticMapper();
    }

}
