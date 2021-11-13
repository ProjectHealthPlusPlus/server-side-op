package com.opencode.healthplusplus.profile.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDoctorResource extends UserResource{
    private Long specialtyId;
    private Long clinicId;
}
