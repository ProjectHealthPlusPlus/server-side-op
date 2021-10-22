package com.opencode.healthplusplus.health.controller;

import com.opencode.healthplusplus.health.domain.entity.ClinicLocation;
import com.opencode.healthplusplus.health.domain.service.ClinicLocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClinicLocationController {

    private final ClinicLocationService clinicLocationService;

    public ClinicLocationController(ClinicLocationService clinicLocationService) {
        this.clinicLocationService = clinicLocationService;
    }

    @GetMapping("/clinicLocations")
    public List<ClinicLocation> getAllClinicLocations() {
        return clinicLocationService.getAll();
    }

    @PostMapping("/clinicLocations")
    public ClinicLocation createClinicLocation(@Valid @RequestBody ClinicLocation clinicLocation) {
        return clinicLocationService.create(clinicLocation);
    }

    @PutMapping("/clinicLocations/{clinicLocationId}")
    public ClinicLocation updateClinicLocation(@PathVariable Long clinicLocationId, @Valid @RequestBody ClinicLocation request) {
        return clinicLocationService.update(clinicLocationId, request);
    }

    @DeleteMapping("/clinicLocations/{clinicLocationId}")
    public ResponseEntity<?> deleteClinicLocation(@PathVariable Long clinicLocationId) {
        return clinicLocationService.delete(clinicLocationId);
    }

}
