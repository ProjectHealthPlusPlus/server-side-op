package com.opencode.healthplusplus.health.resource;

import com.opencode.healthplusplus.health.domain.entity.Diagnostic;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateMedicalHistoryResource {
    private List<Diagnostic> diagnostics;
}
