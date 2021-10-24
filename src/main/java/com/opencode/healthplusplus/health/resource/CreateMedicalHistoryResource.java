package com.opencode.healthplusplus.health.resource;

import com.opencode.healthplusplus.health.domain.entity.Diagnostic;
import com.opencode.healthplusplus.health.domain.entity.Patient;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateMedicalHistoryResource {
    private Patient patient;
    private List<Diagnostic> diagnostics;
}
