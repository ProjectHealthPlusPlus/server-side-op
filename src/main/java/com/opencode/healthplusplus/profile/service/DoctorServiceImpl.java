package com.opencode.healthplusplus.profile.service;

import com.opencode.healthplusplus.meeting.domain.entity.Clinic;
import com.opencode.healthplusplus.meeting.domain.persistence.ClinicRepository;
import com.opencode.healthplusplus.profile.domain.entity.Doctor;
import com.opencode.healthplusplus.profile.domain.entity.Specialty;
import com.opencode.healthplusplus.profile.domain.persistence.DoctorRepository;
import com.opencode.healthplusplus.profile.domain.persistence.SpecialtyRepository;
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
    private final ClinicRepository clinicRepository;
    private final SpecialtyRepository specialtyRepository;
    private final Validator validator;

    public DoctorServiceImpl(DoctorRepository doctorRepository, ClinicRepository clinicRepository, SpecialtyRepository specialtyRepository, Validator validator) {
        this.doctorRepository = doctorRepository;
        this.clinicRepository = clinicRepository;
        this.specialtyRepository = specialtyRepository;
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

        Doctor doctorWithDni = doctorRepository.findByDni(request.getDni());

        if(doctorWithDni != null)
            throw new ResourceValidationException(ENTITY, "A Doctor with the same dni exist");

        return doctorRepository.save(request);
    }

    @Override
    public Doctor update(Long doctorId, Doctor request) {
        Set<ConstraintViolation<Doctor>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        Doctor doctor = doctorRepository.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, doctorId));
//        Clinic clinic = clinicRepository.findAllById(request.getClinicsId())
//                .orElseThrow(() -> new ResourceNotFoundException("Clinics not found"));

        doctor.setClinics(request.getClinics());
        doctor.setSpecialties(request.getSpecialties());
        doctor.setAge(request.getAge());
        doctor.setName(request.getName());
        doctor.setLastName(request.getLastName());
        doctor.setDni(request.getDni());

        return  doctorRepository.save(doctor);
    }

    @Override
    public Doctor addClinics(Long doctorId, List<Long> clinicsId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, doctorId));

        List<Clinic> clinics = clinicRepository.findAllById(clinicsId);

        doctor.addClinics(clinics);

        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor removeClinics(Long doctorId, List<Long> clinicsId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, doctorId));

        List<Clinic> clinics = clinicRepository.findAllById(clinicsId);

        doctor.removeClinics(clinics);

        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor addSpecialties(Long doctorId, List<Long> specialtiesId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, doctorId));

        List<Specialty> specialties = specialtyRepository.findAllById(specialtiesId);

        doctor.addSpecialties(specialties);

        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor removeSpecialties(Long doctorId, List<Long> specialtiesId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, doctorId));

        List<Specialty> specialties = specialtyRepository.findAllById(specialtiesId);

        doctor.removeSpecialties(specialties);

        return doctorRepository.save(doctor);
    }

    @Override
    public ResponseEntity<?> delete(Long doctorId) {
        return doctorRepository.findById(doctorId).map(doctor -> {
            doctorRepository.delete(doctor);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, doctorId));
    }
}
