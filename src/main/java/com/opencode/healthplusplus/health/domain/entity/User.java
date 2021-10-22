package com.opencode.healthplusplus.health.domain.entity;

import com.opencode.healthplusplus.shared.domain.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotNull
    @Digits(integer = 8, fraction = 0)
    @Column(unique = true)
    private int dni;

    @NotNull
    @Size(max = 20)
    private String name;

    @NotNull
    @Size(max = 20)
    private String lastName;

    @NotNull
    @Digits(integer = 3, fraction = 0)
    private int age;
}
