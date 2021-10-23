package com.opencode.healthplusplus.health.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicLocationResource {
    private Long id;
    private String address;
    private String capitalCity;
    private String country;
}
