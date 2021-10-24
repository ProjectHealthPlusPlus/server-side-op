package com.opencode.healthplusplus.health.resource;

import com.opencode.healthplusplus.health.domain.entity.ClinicLocation;
import com.opencode.healthplusplus.health.domain.entity.Doctor;
import com.opencode.healthplusplus.health.domain.entity.MedicalHistory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateClinicResource extends UserResource{
    private ClinicLocation clinicLocation;
    private List<Doctor> doctors;
    private List<MedicalHistory> medicalHistories;
}
