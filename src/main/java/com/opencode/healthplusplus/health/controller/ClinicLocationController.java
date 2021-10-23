package com.opencode.healthplusplus.health.controller;

import com.opencode.healthplusplus.health.domain.entity.ClinicLocation;
import com.opencode.healthplusplus.health.domain.service.ClinicLocationService;
import com.opencode.healthplusplus.health.mapping.ClinicLocationMapper;
import com.opencode.healthplusplus.health.resource.ClinicLocationResource;
import com.opencode.healthplusplus.health.resource.CreateClinicLocationResource;
import com.opencode.healthplusplus.health.resource.UpdateClinicLocationResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clinicLocations")
public class ClinicLocationController {

    private final ClinicLocationService clinicLocationService;
    private final ClinicLocationMapper mapper;

    public ClinicLocationController(ClinicLocationService clinicLocationService, ClinicLocationMapper mapper) {
        this.clinicLocationService = clinicLocationService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<ClinicLocationResource> getAllClinicLocations(Pageable pageable) {
        return mapper.modelListToPage(clinicLocationService.getAll(), pageable);
    }

    @GetMapping("{clinicLocationId}")
    public ClinicLocationResource getClinicLocationById(@PathVariable Long clinicLocationId) {
        return mapper.toResource(clinicLocationService.getById(clinicLocationId));
    }

    @PostMapping
    public ClinicLocationResource createClinicLocation(@RequestBody CreateClinicLocationResource request) {
        return mapper.toResource(clinicLocationService.create(mapper.toModel(request)));
    }

    @PutMapping("{clinicLocationId}")
    public ClinicLocationResource updateClinicLocation(@PathVariable Long clinicLocationId, @RequestBody UpdateClinicLocationResource request) {
        return mapper.toResource(clinicLocationService.update(clinicLocationId, mapper.toModel(request)));
    }

    @DeleteMapping("{clinicLocationId}")
    public ResponseEntity<?> deleteClinicLocation(@PathVariable Long clinicLocationId) {
        return clinicLocationService.delete(clinicLocationId);
    }

}
