package com.opencode.healthplusplus.meeting.resource;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.opencode.healthplusplus.profile.resource.DoctorResource;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ClinicResource {
    private Long id;
    private LocationResource location;
    private List<DoctorResource> doctors;
}
