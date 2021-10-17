package com.opencode.healthplusplus.health.domain.entity;

import com.opencode.healthplusplus.shared.domain.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public abstract class User extends AuditModel {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @NotNull
    @Size(max = 8)
    @Column(unique = true)
    private int dni;

    @NotNull
    @Size(max = 20)
    private String name;

    @NotNull
    @Size(max = 20)
    private String lastName;

    @NotNull
    private int age;
}
