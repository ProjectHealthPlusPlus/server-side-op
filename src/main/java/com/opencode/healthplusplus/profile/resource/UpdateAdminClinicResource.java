package com.opencode.healthplusplus.profile.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAdminClinicResource extends UpdateUserResource{
    private Long clinicId;
    private Long specialtyId;
}
