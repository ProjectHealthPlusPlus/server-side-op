package com.opencode.healthplusplus.meeting.domain.entity;

import com.opencode.healthplusplus.health.domain.entity.Diagnostic;
import com.opencode.healthplusplus.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "appointment_details")
public class AppointmentDetails extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "patient_started_at", nullable = false, updatable = false)
    private Date patientStartedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "doctor_started_at", nullable = false, updatable = false)
    private Date doctorStartedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "patient_ended_at", nullable = false, updatable = false)
    private Date patientEndedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "doctor_ended_at", nullable = false, updatable = false)
    private Date doctorEndedAt;

    @OneToOne
    private Diagnostic diagnostic;
}
