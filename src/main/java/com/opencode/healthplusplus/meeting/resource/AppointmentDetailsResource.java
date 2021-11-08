package com.opencode.healthplusplus.meeting.resource;


import com.opencode.healthplusplus.health.domain.entity.Diagnostic;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AppointmentDetailsResource {
    private Long id;
    private Date patientStartAt;
    private Date doctorStartAt;
    private Date patientEndAt;
    private Date doctorEndAt;
    private Diagnostic diagnostic;
}
