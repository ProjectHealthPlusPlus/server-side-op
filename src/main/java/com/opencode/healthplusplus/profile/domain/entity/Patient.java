package com.opencode.healthplusplus.profile.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opencode.healthplusplus.health.domain.entity.MedicalHistory;
import com.opencode.healthplusplus.meeting.domain.entity.Appointment;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "patients")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Patient extends User {

    @NotNull
    @Size(max = 100)
    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Appointment> appointments;

    @JsonIgnore
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<MedicalHistory> medicalHistories;
}
