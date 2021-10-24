package com.opencode.healthplusplus.health.domain.entity;

import com.opencode.healthplusplus.shared.domain.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "appointment_details")
public class AppointmentDetails extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "patient_start_at", nullable = false, updatable = false)
    private Date patientStartAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "doctor_start_at", nullable = false, updatable = false)
    private Date doctorStartAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "patient_end_at", nullable = false, updatable = false)
    private Date patientEndAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "doctor_end_at", nullable = false, updatable = false)
    private Date doctorEndAt;

    @OneToOne
    private Diagnostic diagnostic;
}
