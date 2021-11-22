package com.opencode.healthplusplus.health.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateMedicalHistoryResource {
    private Long patientId;
    private List<Long> diagnosticsId;
}
