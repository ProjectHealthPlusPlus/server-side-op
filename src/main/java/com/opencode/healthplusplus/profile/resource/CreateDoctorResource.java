package com.opencode.healthplusplus.profile.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateDoctorResource extends CreateUserResource{
    private List<Long> specialtiesId;
    private List<Long> clinicsId;
}
