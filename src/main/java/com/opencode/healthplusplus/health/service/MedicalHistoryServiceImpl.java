package com.opencode.healthplusplus.health.service;

import com.opencode.healthplusplus.health.domain.entity.MedicalHistory;
import com.opencode.healthplusplus.health.domain.persistence.ClinicRepository;
import com.opencode.healthplusplus.health.domain.persistence.MedicalHistoryRepository;
import com.opencode.healthplusplus.health.domain.persistence.PatientRepository;
import com.opencode.healthplusplus.health.domain.service.MedicalHistoryService;
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
public class MedicalHistoryServiceImpl implements MedicalHistoryService {

    private static final String ENTITY = "MedicalHistory";
    private final MedicalHistoryRepository medicalHistoryRepository;
    private final PatientRepository patientRepository;
    private final ClinicRepository clinicRepository;
    private final Validator validator;

    public MedicalHistoryServiceImpl(MedicalHistoryRepository medicalHistoryRepository, PatientRepository patientRepository, ClinicRepository clinicRepository, Validator validator) {
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.patientRepository = patientRepository;
        this.clinicRepository = clinicRepository;
        this.validator = validator;
    }

    // By Patient Id

    @Override
    public List<MedicalHistory> getAllByPatientId(Long patientId) {
        return medicalHistoryRepository.findByPatientId(patientId);
    }

    @Override
    public Page<MedicalHistory> getAllByPatientId(Long patientId, Pageable pageable) {
        return medicalHistoryRepository.findByPatientId(patientId, pageable);
    }

    @Override
    public MedicalHistory getByIdAndByPatientId(Long patientId, Long medicalHistoryId) {
        return medicalHistoryRepository.findByIdAndPatientId(medicalHistoryId, patientId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, medicalHistoryId));
    }


    // By Clinic Id

    @Override
    public List<MedicalHistory> getAllByClinicId(Long clinicId) {
        return medicalHistoryRepository.findByClinicId(clinicId);
    }

    @Override
    public Page<MedicalHistory> getAllByClinicId(Long clinicId, Pageable pageable) {
        return medicalHistoryRepository.findByClinicId(clinicId, pageable);
    }

    @Override
    public MedicalHistory getByIdAndByClinicId(Long clinicId, Long medicalHistoryId) {
        return medicalHistoryRepository.findByIdAndClinicId(medicalHistoryId, clinicId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, medicalHistoryId));
    }

    @Override
    public MedicalHistory create(Long clinicId, MedicalHistory request) {
        Set<ConstraintViolation<MedicalHistory>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return clinicRepository.findById(clinicId).map(clinic -> {
            request.setClinic(clinic);
            return medicalHistoryRepository.save(request);
        }).orElseThrow(() -> new ResourceNotFoundException("Clinic", clinicId));
    }

    @Override
    public MedicalHistory update(Long clinicId, Long medicalHistoryId, MedicalHistory request) {
        Set<ConstraintViolation<MedicalHistory>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if(!clinicRepository.existsById(clinicId))
            throw new ResourceNotFoundException("Clinic", clinicId);

        return medicalHistoryRepository.findById(medicalHistoryId).map(medicalHistory ->
            medicalHistoryRepository.save(medicalHistory.withDiagnostics(request.getDiagnostics())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, medicalHistoryId));
    }

    @Override
    public ResponseEntity<?> delete(Long clinicId, Long medicalHistoryId) {
        return medicalHistoryRepository.findByIdAndClinicId(medicalHistoryId, clinicId).map(medicalHistory -> {
            medicalHistoryRepository.delete(medicalHistory);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, medicalHistoryId));
    }
}
