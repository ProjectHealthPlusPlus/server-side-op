package com.opencode.healthplusplus.profile.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Clinic clinic;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Specialty> specialties;

    public void addSpecialty(Specialty specialty) {
        specialties.add(specialty);
    }
    public void removeSpecialty(Specialty specialty) {
        specialties.remove(specialty);
    }

}
