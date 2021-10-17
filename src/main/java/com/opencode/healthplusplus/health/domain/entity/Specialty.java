package com.opencode.healthplusplus.health.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "specialties")
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(unique = true)
    private String name;

    @NotNull
    @Lob
    private String description;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "specialties")
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "specialty", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Diagnostic> diagnostics;
}
