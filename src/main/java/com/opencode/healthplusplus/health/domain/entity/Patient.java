package com.opencode.healthplusplus.health.domain.entity;

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
@Entity(name = "patients")
public class Patient extends User{

    @NotNull
    @Size(max = 100)
    private String address;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MedicalHistory> medicalHistories;
}
