package com.opencode.healthplusplus.meeting.domain.persistence;

import com.opencode.healthplusplus.meeting.domain.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
