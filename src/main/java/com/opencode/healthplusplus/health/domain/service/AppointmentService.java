package com.opencode.healthplusplus.health.domain.service;

import com.opencode.healthplusplus.health.domain.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAll();
    Page<Appointment> getAll(Pageable pageable);
    Appointment getById(Long appointmentId);
    Appointment create(Appointment request);
    Appointment update(Long appointmentId, Appointment request);
    ResponseEntity<?> delete(Long appointmentId);
}
