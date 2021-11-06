package com.opencode.healthplusplus.profile.domain.service;

import com.opencode.healthplusplus.profile.domain.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PatientService {
    List<Patient> getAll();
    Page<Patient> getAll(Pageable pageable);
    Patient getById(Long patientId);
    Patient create(Patient request);
    Patient update(Long patientId, Patient request);
    ResponseEntity<?> delete(Long patientId);
}
