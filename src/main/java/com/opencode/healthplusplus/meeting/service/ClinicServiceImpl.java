package com.opencode.healthplusplus.meeting.service;

import com.opencode.healthplusplus.meeting.domain.entity.Clinic;
import com.opencode.healthplusplus.meeting.domain.persistence.ClinicRepository;
import com.opencode.healthplusplus.meeting.domain.service.ClinicService;
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
public class ClinicServiceImpl implements ClinicService {

    private static final String ENTITY = "Clinic";
    private final ClinicRepository clinicRepository;
    private final Validator validator;

    public ClinicServiceImpl(ClinicRepository clinicRepository, Validator validator) {
        this.clinicRepository = clinicRepository;
        this.validator = validator;
    }

    @Override
    public List<Clinic> getAll() {
        return clinicRepository.findAll();
    }

    @Override
    public Page<Clinic> getAll(Pageable pageable) {
        return clinicRepository.findAll(pageable);
    }

    @Override
    public Clinic getById(Long clinicId) {
        return clinicRepository.findById(clinicId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, clinicId));
    }

    @Override
    public Clinic create(Clinic request) {
        Set<ConstraintViolation<Clinic>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return clinicRepository.save(request);
    }

    @Override
    public Clinic update(Long clinicId, Clinic request) {
        Set<ConstraintViolation<Clinic>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return clinicRepository.findById(clinicId).map(clinic ->
                clinicRepository.save(
                        clinic.withClinicLocation(request.getClinicLocation())
                                .withDoctors(request.getDoctors())
                                .withMedicalHistories(request.getMedicalHistories())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, clinicId));
    }

    @Override
    public ResponseEntity<?> delete(Long clinicId) {
        return clinicRepository.findById(clinicId).map(clinic -> {
            clinicRepository.delete(clinic);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, clinicId));
    }
}
