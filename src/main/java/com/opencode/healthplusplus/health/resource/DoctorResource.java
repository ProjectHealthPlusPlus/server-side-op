package com.opencode.healthplusplus.health.resource;

import com.opencode.healthplusplus.health.domain.entity.Clinic;
import com.opencode.healthplusplus.health.domain.entity.Specialty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DoctorResource extends UserResource{
    private List<Specialty> specialties;
    private List<Clinic> clinics;
}
