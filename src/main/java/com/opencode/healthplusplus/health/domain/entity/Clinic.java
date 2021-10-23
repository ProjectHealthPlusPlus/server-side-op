package com.opencode.healthplusplus.health.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity(name = "clinics")
public class Clinic extends User{

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "clinic_locations_id", nullable = false)
    @JsonIgnore
    private ClinicLocation clinicLocation;

    @ManyToMany(fetch = FetchType.LAZY
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "clinic_doctors",
            joinColumns = {@JoinColumn(name = "clinic_id")},
            inverseJoinColumns = {@JoinColumn(name = "doctor_id")})
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MedicalHistory> medicalHistories;
}
