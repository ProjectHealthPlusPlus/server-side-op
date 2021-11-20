package com.opencode.healthplusplus.meeting.resource;


import com.opencode.healthplusplus.health.domain.entity.Diagnostic;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class AppointmentDetailsResource {
    private Long id;
    private Date patientStartedAt;
    private Date doctorStartedAt;
    private Date patientEndedAt;
    private Date doctorEndedAt;
    private Diagnostic diagnostic;
}
