package com.opencode.healthplusplus.health.domain.service;

import com.opencode.healthplusplus.health.domain.entity.Diagnostic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DiagnosticService {
    List<Diagnostic> getAllByMedicalHistoryId(Long medicalHistoryId);
    Page<Diagnostic> getAllByMedicalHistoryId(Long medicalHistoryId, Pageable pageable);
    Diagnostic getByIdAndByMedicalHistoryId(Long medicalHistoryId, Long diagnosticId);
    Diagnostic create(Long medicalHistoryId, Diagnostic request);
    Diagnostic update(Long medicalHistoryId, Long diagnosticId, Diagnostic request);
    ResponseEntity<?> delete(Long medicalHistoryId, Long diagnosticId);
}
