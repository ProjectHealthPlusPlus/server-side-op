package com.opencode.healthplusplus.profile.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateDoctorResource extends UserResource{
    private List<Long> specialtiesId;
    private List<Long> clinicsId;
}
