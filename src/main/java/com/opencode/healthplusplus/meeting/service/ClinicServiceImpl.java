package com.opencode.healthplusplus.meeting.service;

import com.opencode.healthplusplus.meeting.domain.entity.Clinic;
import com.opencode.healthplusplus.meeting.domain.entity.Location;
import com.opencode.healthplusplus.meeting.domain.persistence.ClinicRepository;
import com.opencode.healthplusplus.meeting.domain.persistence.LocationRepository;
import com.opencode.healthplusplus.meeting.domain.service.ClinicService;
import com.opencode.healthplusplus.profile.domain.entity.Doctor;
import com.opencode.healthplusplus.profile.domain.persistence.DoctorRepository;
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
    private final DoctorRepository doctorRepository;
    private final LocationRepository locationRepository;
    private final Validator validator;

    public ClinicServiceImpl(ClinicRepository clinicRepository, DoctorRepository doctorRepository, LocationRepository locationRepository, Validator validator) {
        this.clinicRepository = clinicRepository;
        this.doctorRepository = doctorRepository;
        this.locationRepository = locationRepository;
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

        Clinic clinicWithLocation = clinicRepository.findByLocation_Id(request.getLocation().getId());

        if(clinicWithLocation != null)
            throw new ResourceValidationException(ENTITY, "A Clinic with the same Location exit");

        return clinicRepository.save(request);
    }

    @Override
    public Clinic update(Long clinicId, Clinic request) {
        Set<ConstraintViolation<Clinic>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return clinicRepository.findById(clinicId).map(clinic ->
                clinicRepository.save(
                        clinic.withId(request.getId())
                                .withLocation(request.getLocation())
                                .withDoctors(request.getDoctors())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, clinicId));
    }

    @Override
    public Clinic changeLocation(Long clinicId, Long locationId) {

        Clinic clinic = clinicRepository.findById(clinicId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, clinicId));

        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException("Location", locationId));

        clinic.setLocation(location);

        return clinicRepository.save(clinic);
    }

    @Override
    public Clinic addDoctors(Long clinicId, List<Long> doctorsId) {

        Clinic clinic = clinicRepository.findById(clinicId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, clinicId));

        List<Doctor> doctors = doctorRepository.findAllById(doctorsId);

        clinic.addDoctors(doctors);

        return clinicRepository.save(clinic);
    }

    @Override
    public Clinic removeDoctors(Long clinicId, List<Long> doctorsId) {

        Clinic clinic = clinicRepository.findById(clinicId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, clinicId));

        List<Doctor> doctors = doctorRepository.findAllById(doctorsId);

        clinic.removeDoctors(doctors);

        return clinicRepository.save(clinic);
    }

    @Override
    public ResponseEntity<?> delete(Long clinicId) {
        return clinicRepository.findById(clinicId).map(clinic -> {
            clinicRepository.delete(clinic);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, clinicId));
    }
}
