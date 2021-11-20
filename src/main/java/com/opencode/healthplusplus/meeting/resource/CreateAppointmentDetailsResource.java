package com.opencode.healthplusplus.meeting.resource;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class CreateAppointmentDetailsResource {
    private Date patientStartedAt;
    private Date doctorStartedAt;
    private Date patientEndedAt;
    private Date doctorEndedAt;
    private Long diagnosticId;
}
