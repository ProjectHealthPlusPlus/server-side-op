package com.opencode.healthplusplus.meeting.domain.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.opencode.healthplusplus.profile.domain.entity.Doctor;
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
@Table(name = "clinics")
public class Clinic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "locations_id", nullable = false)
    @JsonIgnore
    private Location location;

    @ManyToMany(fetch = FetchType.LAZY
            , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "clinics_doctors",
            joinColumns = {@JoinColumn(name = "clinic_id")},
            inverseJoinColumns = {@JoinColumn(name = "doctor_id")})
    @JsonIgnore
    private List<Doctor> doctors;

    public void addDoctors(List<Doctor> doctors) {
        this.doctors.addAll(doctors);
    }
    public void removeDoctors(List<Doctor> doctors) {
        for (Doctor doctor: doctors)
            this.doctors.remove(doctor);
    }
    
}
