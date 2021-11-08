package com.opencode.healthplusplus.profile.domain.entity;

import com.opencode.healthplusplus.profile.domain.entity.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity(name = "patients")
public class Patient extends User {

    @NotNull
    @Size(max = 100)
    private String address;
}
