package com.opencode.healthplusplus.profile.resource;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.opencode.healthplusplus.meeting.resource.ClinicResource;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class DoctorResource extends UserResource {
    private List<SpecialtyResource> specialties;
    private List<ClinicResource> clinics;
}
