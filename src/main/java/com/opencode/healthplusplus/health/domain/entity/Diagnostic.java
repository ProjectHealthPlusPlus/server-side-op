package com.opencode.healthplusplus.health.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opencode.healthplusplus.shared.domain.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "diagnostics")
public class Diagnostic extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "publish_date", nullable = false, updatable = false)
    private Date PublishDate;

    @NotNull
    @Lob
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "specialty_id", nullable = false)
    @JsonIgnore
    private Specialty specialty;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "medical_history_id", nullable = false)
    @JsonIgnore
    private MedicalHistory medicalHistory;
}
