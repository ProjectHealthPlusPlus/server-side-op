package com.opencode.healthplusplus.meeting.resource;

import com.opencode.healthplusplus.meeting.domain.entity.AppointmentDetails;
import com.opencode.healthplusplus.profile.domain.entity.Doctor;
import com.opencode.healthplusplus.profile.domain.entity.Patient;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AppointmentResource {
    private Long id;
    private Date startAt;
    private Patient patient;
    private Doctor doctor;
    private AppointmentDetails appointmentDetails;
}
