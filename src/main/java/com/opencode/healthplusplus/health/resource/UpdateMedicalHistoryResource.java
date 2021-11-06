package com.opencode.healthplusplus.health.resource;

import com.opencode.healthplusplus.health.domain.entity.Diagnostic;
import com.opencode.healthplusplus.profile.domain.entity.Patient;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateMedicalHistoryResource {
    private Long id;
    private Patient patient;
    private List<Diagnostic> diagnostics;
}
