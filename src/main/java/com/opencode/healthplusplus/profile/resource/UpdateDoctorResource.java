package com.opencode.healthplusplus.profile.resource;

import com.opencode.healthplusplus.meeting.domain.entity.Clinic;
import com.opencode.healthplusplus.profile.domain.entity.Specialty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateDoctorResource extends UserResource{

    private List<Specialty> specialties;
    private List<Clinic> clinics;
}
