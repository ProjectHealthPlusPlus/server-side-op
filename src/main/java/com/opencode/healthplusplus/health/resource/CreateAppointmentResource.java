package com.opencode.healthplusplus.health.resource;

import com.opencode.healthplusplus.health.domain.entity.AppointmentDetails;
import com.opencode.healthplusplus.health.domain.entity.Doctor;
import com.opencode.healthplusplus.health.domain.entity.Patient;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateAppointmentResource {
    private Date startAt;
    private Patient patient;
    private Doctor doctor;
    private AppointmentDetails appointmentDetails;
}
