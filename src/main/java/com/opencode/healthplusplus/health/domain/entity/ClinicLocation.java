package com.opencode.healthplusplus.health.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "clinic_locations")
public class ClinicLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String address;

    @NotNull
    @Size(max = 20)
    private String capitalCity;

    @NotNull
    @Size(max = 20)
    private String country;
}
