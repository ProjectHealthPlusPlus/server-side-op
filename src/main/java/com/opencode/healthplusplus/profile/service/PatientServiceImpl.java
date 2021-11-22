package com.opencode.healthplusplus.profile.service;

import com.opencode.healthplusplus.profile.domain.entity.Patient;
import com.opencode.healthplusplus.profile.domain.persistence.PatientRepository;
import com.opencode.healthplusplus.profile.domain.service.PatientService;
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
public class PatientServiceImpl implements PatientService {

    private static final String ENTITY = "Patient";
    private final PatientRepository patientRepository;
    private final Validator validator;

    public PatientServiceImpl(PatientRepository patientRepository, Validator validator) {
        this.patientRepository = patientRepository;
        this.validator = validator;
    }


    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public Page<Patient> getAll(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    @Override
    public Patient getById(Long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, patientId));
    }

    @Override
    public Patient create(Patient request) {
        Set<ConstraintViolation<Patient>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Patient patientWithDni = patientRepository.findByDni(request.getDni());

        if(patientWithDni != null)
            throw new ResourceValidationException(ENTITY, "A Patient with the same dni exist");

        return patientRepository.save(request);
    }

    @Override
    public Patient update(Long patientId, Patient request) {
        Set<ConstraintViolation<Patient>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);


        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY,patientId));
        patient.setAddress(request.getAddress());
        patient.setAge(request.getAge());
        patient.setDni(request.getDni());
        patient.setName(request.getName());
        patient.setLastName(request.getLastName());

        return patientRepository.save(patient);

    }

    @Override
    public ResponseEntity<?> delete(Long patientId) {
        return patientRepository.findById(patientId).map(patient -> {
            patientRepository.delete(patient);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, patientId));
    }
}
