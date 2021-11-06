package com.opencode.healthplusplus.profile.service;

import com.opencode.healthplusplus.profile.domain.entity.Doctor;
import com.opencode.healthplusplus.profile.domain.persistence.DoctorRepository;
import com.opencode.healthplusplus.profile.domain.service.DoctorService;
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
public class DoctorServiceImpl implements DoctorService {

    private static final String ENTITY = "Doctor";
    private final DoctorRepository doctorRepository;
    private final Validator validator;

    public DoctorServiceImpl(DoctorRepository doctorRepository, Validator validator) {
        this.doctorRepository = doctorRepository;
        this.validator = validator;
    }


    @Override
    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Page<Doctor> getAll(Pageable pageable) {
        return doctorRepository.findAll(pageable);
    }

    @Override
    public Doctor getById(Long doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, doctorId));
    }

    @Override
    public Doctor create(Doctor request) {
        Set<ConstraintViolation<Doctor>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return doctorRepository.save(request);
    }

    @Override
    public Doctor update(Long doctorId, Doctor request) {
        Set<ConstraintViolation<Doctor>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return doctorRepository.findById(doctorId).map(doctor ->
                        doctorRepository.save(
                                doctor.withSpecialties(request.getSpecialties())
                                        .withClinics(request.getClinics())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, doctorId));
    }

    @Override
    public ResponseEntity<?> delete(Long doctorId) {
        return doctorRepository.findById(doctorId).map(doctor -> {
            doctorRepository.delete(doctor);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, doctorId));
    }
}
