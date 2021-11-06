package com.opencode.healthplusplus.meeting.domain.service;

import com.opencode.healthplusplus.meeting.domain.entity.AppointmentDetails;
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
