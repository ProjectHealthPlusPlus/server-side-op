package com.opencode.healthplusplus.health.domain.persistence;

import com.opencode.healthplusplus.health.domain.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
