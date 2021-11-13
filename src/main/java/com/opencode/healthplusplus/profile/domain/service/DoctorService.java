package com.opencode.healthplusplus.profile.domain.service;

import com.opencode.healthplusplus.profile.domain.entity.Doctor;
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
    Doctor addClinic(Long doctorId, Long clinicId);
    Doctor deleteClinic(Long doctorId, Long clinicId);
    Doctor addSpecialty(Long doctorId, Long specialtyId);
    Doctor deleteSpecialty(Long doctorId, Long specialtyId);
    ResponseEntity<?> delete(Long doctorId);
}
