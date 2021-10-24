package com.opencode.healthplusplus.health.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("healthMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public ClinicLocationMapper clinicLocationMapper() {
        return new ClinicLocationMapper();
    }

    @Bean
    public  ClinicMapper clinicMapper() {
        return new ClinicMapper();
    }

    @Bean
    public  SpecialtyMapper specialtyMapper() {
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
    public AppointmentMapper appointmentMapper() {
        return new AppointmentMapper();
    }

    @Bean
    public AppointmentDetailsMapper appointmentDetailsMapper() {
        return new AppointmentDetailsMapper();
    }

    @Bean
    public MedicalHistoryMapper medicalHistoryMapper() {
        return new MedicalHistoryMapper();
    }

}
