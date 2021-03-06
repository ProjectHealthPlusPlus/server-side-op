package com.opencode.healthplusplus.meeting.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UpdateAppointmentDetailsResource {
    private Long id;
    private Date patientStartedAt;
    private Date doctorStartedAt;
    private Date patientEndedAt;
    private Date doctorEndedAt;
    private Long diagnosticId;
}
