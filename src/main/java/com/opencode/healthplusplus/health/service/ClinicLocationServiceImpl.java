package com.opencode.healthplusplus.health.service;

import com.opencode.healthplusplus.health.domain.entity.ClinicLocation;
import com.opencode.healthplusplus.health.domain.persistence.ClinicLocationRepository;
import com.opencode.healthplusplus.health.domain.service.ClinicLocationService;
import com.opencode.healthplusplus.shared.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicLocationServiceImpl implements ClinicLocationService {

    private final ClinicLocationRepository clinicLocationRepository;

    public ClinicLocationServiceImpl(ClinicLocationRepository clinicLocationRepository) {
        this.clinicLocationRepository = clinicLocationRepository;
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
    public ClinicLocation create(ClinicLocation clinicLocation) {
        return clinicLocationRepository.save(clinicLocation);
    }

    @Override
    public ClinicLocation update(Long clinicLocationId, ClinicLocation request) {
        return clinicLocationRepository.findById(clinicLocationId).map(clinicLocation -> {
            clinicLocation.setAddress(request.getAddress());
            clinicLocation.setCapitalCity(request.getCapitalCity());
            clinicLocation.setCountry(request.getCountry());
            return clinicLocationRepository.save(clinicLocation);
        }).orElseThrow(() -> new ResourceNotFoundException("ClinicLocation", clinicLocationId));
    }

    @Override
    public ResponseEntity<?> delete(Long clinicLocationId) {
        return clinicLocationRepository.findById(clinicLocationId).map(clinicLocation -> {
            clinicLocationRepository.delete(clinicLocation);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("ClinicLocation", clinicLocationId));
    }
}
