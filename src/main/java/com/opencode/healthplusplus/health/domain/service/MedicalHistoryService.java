package com.opencode.healthplusplus.health.domain.service;

import com.opencode.healthplusplus.health.domain.entity.MedicalHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MedicalHistoryService {
    // By Patient Id
    List<MedicalHistory> getAllByPatientId(Long patientId);
    Page<MedicalHistory> getAllByPatientId(Long patientId, Pageable pageable);
    MedicalHistory getByIdAndByPatientId(Long patientId, Long medicalHistoryId);

    // By Clinic Id
    List<MedicalHistory> getAllByClinicId(Long clinicId);
    Page<MedicalHistory> getAllByClinicId(Long clinicId, Pageable pageable);
    MedicalHistory getByIdAndByClinicId(Long clinicId, Long medicalHistoryId);
    MedicalHistory create(Long clinicId, MedicalHistory request);
    MedicalHistory update(Long clinicId, Long medicalHistoryId, MedicalHistory request);
    ResponseEntity<?> delete(Long clinicId, Long medicalHistoryId);
}
