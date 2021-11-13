package com.opencode.healthplusplus.meeting.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencode.healthplusplus.health.domain.entity.MedicalHistory;
import com.opencode.healthplusplus.profile.domain.entity.AdminClinic;
import com.opencode.healthplusplus.profile.domain.entity.Doctor;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "clinics")
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "admin_clinic_id")
    @JsonIgnore
    private AdminClinic adminClinic;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "locations_id", nullable = false)
    @JsonIgnore
    private Location location;

    @ManyToMany(fetch = FetchType.LAZY
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "clinic_doctors",
            joinColumns = {@JoinColumn(name = "clinic_id")},
            inverseJoinColumns = {@JoinColumn(name = "doctor_id")})
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MedicalHistory> medicalHistories;
    
}
