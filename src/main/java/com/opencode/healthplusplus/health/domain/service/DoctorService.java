package com.opencode.healthplusplus.health.domain.service;

import com.opencode.healthplusplus.health.domain.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DoctorService {
    List<Doctor> getAll();
    Page<Doctor> getAll(Pageable pageable);
    Doctor getById(Long doctorId);
    Doctor create(Doctor request);
    Doctor update(Long doctorId, Doctor request);
    ResponseEntity<?> delete(Long doctorId);
}
