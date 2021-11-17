package com.opencode.healthplusplus.profile.service;

import com.opencode.healthplusplus.meeting.domain.entity.Clinic;
import com.opencode.healthplusplus.meeting.domain.persistence.ClinicRepository;
import com.opencode.healthplusplus.profile.domain.entity.AdminClinic;
import com.opencode.healthplusplus.profile.domain.entity.Specialty;
import com.opencode.healthplusplus.profile.domain.persistence.AdminClinicRepository;
import com.opencode.healthplusplus.profile.domain.persistence.SpecialtyRepository;
import com.opencode.healthplusplus.profile.domain.service.AdminClinicService;
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
public class AdminClinicServiceImpl implements AdminClinicService {

    private static final String ENTITY = "Admin Clinic";
    private final AdminClinicRepository adminClinicRepository;
    private final ClinicRepository clinicRepository;
    private final SpecialtyRepository specialtyRepository;
    private final Validator validator;

    public AdminClinicServiceImpl(AdminClinicRepository adminClinicRepository, ClinicRepository clinicRepository, SpecialtyRepository specialtyRepository, Validator validator) {
        this.adminClinicRepository = adminClinicRepository;
        this.clinicRepository = clinicRepository;
        this.specialtyRepository = specialtyRepository;
        this.validator = validator;
    }


    @Override
    public List<AdminClinic> getAll() {
        return adminClinicRepository.findAll();
    }

    @Override
    public Page<AdminClinic> getAll(Pageable pageable) {
        return adminClinicRepository.findAll(pageable);
    }

    @Override
    public AdminClinic getById(Long adminClinicId) {
        return adminClinicRepository.findById(adminClinicId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, adminClinicId));
    }

    @Override
    public AdminClinic create(AdminClinic request) {
        Set<ConstraintViolation<AdminClinic>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        AdminClinic adminClinicWithDni = adminClinicRepository.findByDni(request.getDni());

        if(adminClinicWithDni != null)
            throw new ResourceValidationException(ENTITY, "An Admin Clinic with the same dni exist");

        return adminClinicRepository.save(request);

    }

    @Override
    public AdminClinic update(Long adminClinicId, AdminClinic request) {
        Set<ConstraintViolation<AdminClinic>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        AdminClinic adminClinic = adminClinicRepository.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, adminClinicId));
        Clinic clinic = clinicRepository.findById(request.getClinic().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Clinic", request.getClinic().getId()));

        adminClinic.setClinic(clinic);
        adminClinic.setSpecialties(request.getSpecialties());
        adminClinic.setAge(request.getAge());
        adminClinic.setName(request.getName());
        adminClinic.setLastName(request.getLastName());
        adminClinic.setDni(request.getDni());

        return adminClinicRepository.save(adminClinic);
    }

    @Override
    public AdminClinic addSpecialty(Long adminClinicId, Long specialtyId) {

        AdminClinic adminClinic = adminClinicRepository.findById(adminClinicId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, adminClinicId));

        Specialty specialty = specialtyRepository.findById(specialtyId)
                .orElseThrow(() -> new ResourceNotFoundException("Specialty", specialtyId));

        adminClinic.addSpecialty(specialty);

        return adminClinicRepository.save(adminClinic);
    }

    @Override
    public AdminClinic removeSpecialty(Long adminClinicId, Long specialtyId) {

        AdminClinic adminClinic = adminClinicRepository.findById(adminClinicId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, adminClinicId));

        Specialty specialty = specialtyRepository.findById(specialtyId)
                .orElseThrow(() -> new ResourceNotFoundException("Specialty", specialtyId));

        adminClinic.removeSpecialty(specialty);

        return adminClinicRepository.save(adminClinic);
    }

    @Override
    public ResponseEntity<?> delete(Long adminClinicId) {
        return adminClinicRepository.findById(adminClinicId).map(adminClinic -> {
            adminClinicRepository.delete(adminClinic);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, adminClinicId));
    }
}
