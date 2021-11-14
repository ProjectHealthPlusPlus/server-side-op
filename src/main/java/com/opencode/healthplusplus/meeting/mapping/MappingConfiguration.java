package com.opencode.healthplusplus.meeting.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("citationMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public LocationMapper clinicLocationMapper() {
        return new LocationMapper();
    }

    @Bean
    public ClinicMapper clinicMapper() {
        return new ClinicMapper();
    }

    @Bean
    public AppointmentMapper appointmentMapper() {
        return new AppointmentMapper();
    }

    @Bean
    public AppointmentDetailsMapper appointmentDetailsMapper() {
        return new AppointmentDetailsMapper();
    }


}
