package com.opencode.healthplusplus.meeting.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateAppointmentResource {
    private Date startAt;
    private Long patientId;
    private Long doctorId;
    private Long appointmentDetailsId;
}
