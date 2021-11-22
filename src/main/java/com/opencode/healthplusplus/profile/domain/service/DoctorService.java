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
    Doctor addClinics(Long doctorId, List<Long> clinicsId);
    Doctor removeClinics(Long doctorId, List<Long> clinicsId);
    Doctor addSpecialties(Long doctorId, List<Long> specialtiesId);
    Doctor removeSpecialties(Long doctorId, List<Long> specialtiesId);
    ResponseEntity<?> delete(Long doctorId);
}
