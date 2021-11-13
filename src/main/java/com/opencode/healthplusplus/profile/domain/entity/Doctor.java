package com.opencode.healthplusplus.profile.domain.entity;

import com.opencode.healthplusplus.meeting.domain.entity.Clinic;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "doctors")
public class Doctor extends User {

    @ManyToMany(fetch = FetchType.LAZY
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "doctor_specialties",
            joinColumns = {@JoinColumn(name = "doctor_id")},
            inverseJoinColumns = {@JoinColumn(name = "specialty_id")})
    private List<Specialty> specialties;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "doctors")
    private List<Clinic> clinics;



    public void addClinic(Clinic clinic) {
        clinics.add(clinic);
    }
    public void removeClinic(Clinic clinic) {
        clinics.remove(clinic);
    }
    public void addSpecialty(Specialty specialty) {
        specialties.add(specialty);
    }
    public void removeSpecialty(Specialty specialty) {
        specialties.remove(specialty);
    }

}
