package com.opencode.healthplusplus.profile.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opencode.healthplusplus.health.domain.entity.Diagnostic;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "specialties")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Specialty implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String name;

    @NotNull
    @NotBlank
    @Lob
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "specialties")
    private List<Doctor> doctors;

    @JsonIgnore
    @OneToMany(mappedBy = "specialty", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Diagnostic> diagnostics;

}
