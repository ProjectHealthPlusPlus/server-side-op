package com.opencode.healthplusplus.profile.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "patients")
public class Patient extends User {

    @NotNull
    @Size(max = 100)
    private String address;

}
