package com.opencode.healthplusplus.health.service;

import com.opencode.healthplusplus.health.domain.entity.Diagnostic;
import com.opencode.healthplusplus.health.domain.persistence.DiagnosticRepository;
import com.opencode.healthplusplus.health.domain.persistence.MedicalHistoryRepository;
import com.opencode.healthplusplus.health.domain.service.DiagnosticService;
import com.opencode.healthplusplus.shared.exception.ResourceNotFoundException;
import com.opencode.healthplusplus.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class DiagnosticServiceImple implements DiagnosticService {

    private static final String ENTITY = "Diagnostic";
    private final DiagnosticRepository diagnosticRepository;
    private final MedicalHistoryRepository medicalHistoryRepository;
    private final Validator validator;

    public DiagnosticServiceImple(DiagnosticRepository diagnosticRepository, MedicalHistoryRepository medicalHistoryRepository, Validator validator) {
        this.diagnosticRepository = diagnosticRepository;
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.validator = validator;
    }

    @Override
    public List<Diagnostic> getAllByMedicalHistoryId(Long medicalHistoryId) {
        return diagnosticRepository.findByMedicalHistoryId(medicalHistoryId);
    }

    @Override
    public Page<Diagnostic> getAllByMedicalHistoryId(Long medicalHistoryId, Pageable pageable) {
        return diagnosticRepository.findByMedicalHistoryId(medicalHistoryId, pageable);
    }

    @Override
    public Diagnostic getByIdAndByMedicalHistoryId(Long medicalHistoryId, Long diagnosticId) {
        return diagnosticRepository.findByIdAndMedicalHistoryId(diagnosticId, medicalHistoryId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, diagnosticId));
    }

    @Override
    public Diagnostic create(Long medicalHistoryId, Diagnostic request) {
        Set<ConstraintViolation<Diagnostic>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return medicalHistoryRepository.findById(medicalHistoryId).map(medicalHistory -> {
            request.setMedicalHistory(medicalHistory);
            return diagnosticRepository.save(request);
        }).orElseThrow(() -> new ResourceNotFoundException("MedicalHistory", medicalHistoryId));
    }

    @Override
    public Diagnostic update(Long medicalHistoryId, Long diagnosticId, Diagnostic request) {
        Set<ConstraintViolation<Diagnostic>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if(!medicalHistoryRepository.existsById(medicalHistoryId))
            throw new ResourceNotFoundException("MedicalHistory", medicalHistoryId);

        return diagnosticRepository.findById(diagnosticId).map(diagnostic ->
                diagnosticRepository.save(diagnostic.withPublishDate(request.getPublishDate())
                        .withDescription(request.getDescription())
                        .withDescription(request.getDescription())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, diagnosticId));
    }

    @Override
    public ResponseEntity<?> delete(Long medicalHistoryId, Long diagnosticId) {
        return diagnosticRepository.findByIdAndMedicalHistoryId(diagnosticId, medicalHistoryId).map(diagnostic -> {
            diagnosticRepository.delete(diagnostic);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, diagnosticId));
    }
}
