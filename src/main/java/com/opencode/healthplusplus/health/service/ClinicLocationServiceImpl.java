package com.opencode.healthplusplus.health.service;

import com.opencode.healthplusplus.health.domain.entity.ClinicLocation;
import com.opencode.healthplusplus.health.domain.persistence.ClinicLocationRepository;
import com.opencode.healthplusplus.health.domain.service.ClinicLocationService;
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
public class ClinicLocationServiceImpl implements ClinicLocationService {

    private static final String ENTITY = "ClinicLocation";
    private final ClinicLocationRepository clinicLocationRepository;
    private final Validator validator;

    public ClinicLocationServiceImpl(ClinicLocationRepository clinicLocationRepository, Validator validator) {
        this.clinicLocationRepository = clinicLocationRepository;
        this.validator = validator;
    }

    @Override
    public List<ClinicLocation> getAll() {
        return clinicLocationRepository.findAll();
    }

    @Override
    public Page<ClinicLocation> getAll(Pageable pageable) {
        return clinicLocationRepository.findAll(pageable);
    }

    @Override
    public ClinicLocation getById(Long clinicLocationId) {
        return clinicLocationRepository.findById(clinicLocationId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, clinicLocationId));
    }

    @Override
    public ClinicLocation create(ClinicLocation request) {
        Set<ConstraintViolation<ClinicLocation>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return clinicLocationRepository.save(request);
    }

    @Override
    public ClinicLocation update(Long clinicLocationId, ClinicLocation request) {
        Set<ConstraintViolation<ClinicLocation>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return clinicLocationRepository.findById(clinicLocationId).map(clinicLocation ->
                clinicLocationRepository.save(
                        clinicLocation.withAddress(request.getAddress())
                                        .withCapitalCity(request.getCapitalCity())
                                        .withCountry(request.getCountry())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, clinicLocationId));
    }

    @Override
    public ResponseEntity<?> delete(Long clinicLocationId) {
        return clinicLocationRepository.findById(clinicLocationId).map(clinicLocation -> {
            clinicLocationRepository.delete(clinicLocation);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, clinicLocationId));
    }
}
