package com.opencode.healthplusplus.health.domain.service;

import com.opencode.healthplusplus.health.domain.entity.AppointmentDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppointmentDetailsService {
    List<AppointmentDetails> getAll();
    Page<AppointmentDetails> getAll(Pageable pageable);
    AppointmentDetails getById(Long appointmentDetailsId);
    AppointmentDetails create(AppointmentDetails request);
    AppointmentDetails update(Long appointmentDetailsId, AppointmentDetails request);
    ResponseEntity<?> delete(Long appointmentDetailsId);
}
