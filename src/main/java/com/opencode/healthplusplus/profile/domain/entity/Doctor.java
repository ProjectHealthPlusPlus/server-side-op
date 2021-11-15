package com.opencode.healthplusplus.profile.domain.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.opencode.healthplusplus.meeting.domain.entity.Clinic;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "doctors")
//@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
public class Doctor extends User implements Serializable {

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "doctors_specialties",
            joinColumns = {@JoinColumn(name = "doctor_id")},
            inverseJoinColumns = {@JoinColumn(name = "specialty_id")})
    private List<Specialty> specialties;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "doctors")
    private List<Clinic> clinics;



    public void addClinics(List<Clinic> clinics) {
        this.clinics.addAll(clinics);
    }
    public void removeClinics(List<Clinic> clinics) {
        for (Clinic clinic: clinics)
            this.clinics.remove(clinic);
    }
    public void addSpecialties(List<Specialty> specialties) {
        this.specialties.addAll(specialties);
    }
    public void removeSpecialties(List<Specialty> specialties) {
        for (Specialty specialty: specialties)
            this.specialties.remove(specialty);
    }

}
