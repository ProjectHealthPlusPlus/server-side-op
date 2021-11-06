package com.opencode.healthplusplus.profile.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientResource extends UserResource{
    private String address;
}
