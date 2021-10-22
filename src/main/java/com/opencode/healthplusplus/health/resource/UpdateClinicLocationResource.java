package com.opencode.healthplusplus.health.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateClinicLocationResource {

    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String address;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String capitalCity;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String country;
}
