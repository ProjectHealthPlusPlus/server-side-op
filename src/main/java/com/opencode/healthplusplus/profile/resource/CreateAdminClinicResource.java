package com.opencode.healthplusplus.profile.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateAdminClinicResource extends CreateUserResource{
    private Long clinicId;
    private List<Long> specialtiesId;
}
