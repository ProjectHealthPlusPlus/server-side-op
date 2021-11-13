package com.opencode.healthplusplus.profile.domain.entity;

import com.opencode.healthplusplus.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long id;

    @NotNull
    @Digits(integer = 8, fraction = 0)
    @Column(unique = true)
    private int dni;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String lastName;

    @NotNull
    @Digits(integer = 3, fraction = 0)
    private int age;

}
