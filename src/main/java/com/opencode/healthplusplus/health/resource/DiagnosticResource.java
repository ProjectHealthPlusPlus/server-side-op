package com.opencode.healthplusplus.health.resource;

import com.opencode.healthplusplus.health.domain.entity.Specialty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DiagnosticResource {
    private Long id;
    private Date PublishDate;
    private String description;
    private Specialty specialty;
}
