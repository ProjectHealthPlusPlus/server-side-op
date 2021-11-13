package com.opencode.healthplusplus.profile.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("profileMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public SpecialtyMapper specialtyMapper() {
        return new SpecialtyMapper();
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

    @Bean
    public PatientMapper patientMapper() {
        return new PatientMapper();
    }

    @Bean
    public DoctorMapper doctorMapper() {
        return new DoctorMapper();
    }

    @Bean
    public AdminClinicMapper adminClinicMapper() {
        return new AdminClinicMapper();
    }

}
