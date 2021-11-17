package com.opencode.healthplusplus.profile.domain.entity;

import com.opencode.healthplusplus.meeting.domain.entity.Clinic;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "admins_clinics")
public class AdminClinic extends User{

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "clinic_id", nullable = false)
    private Clinic clinic;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "admins_clinics_specialties",
            joinColumns = {@JoinColumn(name = "admin_clinic_id")},
            inverseJoinColumns = {@JoinColumn(name = "specialty_id")})
    private List<Specialty> specialties;

    public void addSpecialties(List<Specialty> specialties) {
        this.specialties.addAll(specialties);
    }
    public void removeSpecialties(List<Specialty> specialties) {
        for (Specialty specialty: specialties)
            this.specialties.remove(specialty);
    }

}
