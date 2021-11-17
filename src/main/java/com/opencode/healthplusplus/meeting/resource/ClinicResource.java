package com.opencode.healthplusplus.meeting.resource;

import com.opencode.healthplusplus.meeting.domain.entity.Location;
import com.opencode.healthplusplus.profile.domain.entity.AdminClinic;
import com.opencode.healthplusplus.profile.resource.UserResource;
import com.opencode.healthplusplus.profile.domain.entity.Doctor;
import com.opencode.healthplusplus.health.domain.entity.MedicalHistory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClinicResource {
    private Long id;
    private Location location;
    private List<Doctor> doctors;
}
