package com.opencode.healthplusplus.health.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdatePatientResource extends UserResource{
    @NotNull
    @Size(max = 100)
    private String address;
}
