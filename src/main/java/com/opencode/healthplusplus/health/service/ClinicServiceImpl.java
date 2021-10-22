package com.opencode.healthplusplus.health.service;

import com.opencode.healthplusplus.health.domain.entity.Clinic;
import com.opencode.healthplusplus.health.domain.persistence.ClinicRepository;
import com.opencode.healthplusplus.health.domain.service.ClinicService;
import com.opencode.healthplusplus.shared.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicServiceImpl implements ClinicService {

    private final ClinicRepository clinicRepository;

    public ClinicServiceImpl(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
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
    public Clinic create(Clinic clinic) {
        return clinicRepository.save(clinic);
    }

    @Override
    public Clinic update(Long clinicId, Clinic request) {
        return clinicRepository.findById(clinicId).map(clinic -> {
            clinic.setClinicLocation(request.getClinicLocation());
            clinic.setDoctors(request.getDoctors());
            clinic.setMedicalHistories(request.getMedicalHistories());
            return  clinicRepository.save(clinic);
        }).orElseThrow(() -> new ResourceNotFoundException("Clinic", clinicId));
    }

    @Override
    public ResponseEntity<?> delete(Long clinicId) {
        return clinicRepository.findById(clinicId).map(clinic -> {
            clinicRepository.delete(clinic);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Clinic", clinicId));
    }
}
