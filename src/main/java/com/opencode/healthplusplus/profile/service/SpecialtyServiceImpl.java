package com.opencode.healthplusplus.profile.service;

import com.opencode.healthplusplus.profile.domain.entity.Specialty;
import com.opencode.healthplusplus.profile.domain.persistence.SpecialtyRepository;
import com.opencode.healthplusplus.profile.domain.service.SpecialtyService;
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
public class SpecialtyServiceImpl implements SpecialtyService {

    private static final String ENTITY = "Specialty";
    private final SpecialtyRepository specialtyRepository;
    private final Validator validator;

    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository, Validator validator) {
        this.specialtyRepository = specialtyRepository;
        this.validator = validator;
    }

    @Override
    public List<Specialty> getAll() {
        return specialtyRepository.findAll();
    }

    @Override
    public Page<Specialty> getAll(Pageable pageable) {
        return specialtyRepository.findAll(pageable);
    }

    @Override
    public Specialty getById(Long specialtyId) {
        return specialtyRepository.findById(specialtyId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, specialtyId));
    }

    @Override
    public Specialty create(Specialty request) {
        Set<ConstraintViolation<Specialty>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);


        // Name uniqueness validation

        Specialty specialtyWithName = specialtyRepository.findByName(request.getName());

        if(specialtyWithName != null)
            throw new ResourceValidationException(ENTITY, "A Specialty with the same name exist");

        return specialtyRepository.save(request);
    }

    @Override
    public Specialty update(Long specialtyId, Specialty request) {
        Set<ConstraintViolation<Specialty>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        // Name uniqueness validation

        Specialty specialtyWithName = specialtyRepository.findByName(request.getName());

        if(specialtyWithName != null && specialtyWithName.getId() != request.getId())
            throw new ResourceValidationException(ENTITY, "A Specialty with the same name exist");

        return specialtyRepository.findById(specialtyId).map(specialty ->
                specialtyRepository.save(
                        specialty.withName(request.getName())
                                .withDescription(request.getDescription())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, specialtyId));
    }

    @Override
    public ResponseEntity<?> delete(Long specialtyId) {
        return specialtyRepository.findById(specialtyId).map(specialty -> {
            specialtyRepository.delete(specialty);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, specialtyId));
    }
}
