package com.opencode.healthplusplus.meeting.service;

import com.opencode.healthplusplus.meeting.domain.entity.Location;
import com.opencode.healthplusplus.meeting.domain.persistence.LocationRepository;
import com.opencode.healthplusplus.meeting.domain.service.LocationService;
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
public class LocationServiceImpl implements LocationService {

    private static final String ENTITY = "Location";
    private final LocationRepository locationRepository;
    private final Validator validator;

    public LocationServiceImpl(LocationRepository locationRepository, Validator validator) {
        this.locationRepository = locationRepository;
        this.validator = validator;
    }

    @Override
    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    @Override
    public Page<Location> getAll(Pageable pageable) {
        return locationRepository.findAll(pageable);
    }

    @Override
    public Location getById(Long clinicLocationId) {
        return locationRepository.findById(clinicLocationId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, clinicLocationId));
    }

    @Override
    public Location create(Location request) {
        Set<ConstraintViolation<Location>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return locationRepository.save(request);
    }

    @Override
    public Location update(Long clinicLocationId, Location request) {
        Set<ConstraintViolation<Location>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return locationRepository.findById(clinicLocationId).map(clinicLocation ->
                locationRepository.save(
                        clinicLocation.withAddress(request.getAddress())
                                        .withCity(request.getCity())
                                        .withCountry(request.getCountry())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, clinicLocationId));
    }

    @Override
    public ResponseEntity<?> delete(Long clinicLocationId) {
        return locationRepository.findById(clinicLocationId).map(clinicLocation -> {
            locationRepository.delete(clinicLocation);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, clinicLocationId));
    }
}
