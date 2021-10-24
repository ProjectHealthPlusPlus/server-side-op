package com.opencode.healthplusplus.health.resource;

import com.opencode.healthplusplus.health.domain.entity.Specialty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class UpdateDiagnosticResource {
    private Long id;
    private Date PublishDate;

    @NotNull
    @NotBlank
    private String description;

    private Specialty specialty;
}
