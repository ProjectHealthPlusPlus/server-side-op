package com.opencode.healthplusplus.meeting.controller;

import com.opencode.healthplusplus.meeting.domain.service.ClinicService;
import com.opencode.healthplusplus.meeting.mapping.ClinicMapper;
import com.opencode.healthplusplus.meeting.resource.ClinicResource;
import com.opencode.healthplusplus.meeting.resource.CreateClinicResource;
import com.opencode.healthplusplus.meeting.resource.UpdateClinicResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clinics")
public class ClinicController {

    private final ClinicService clinicService;
    private final ClinicMapper mapper;

    public ClinicController(ClinicService clinicService, ClinicMapper mapper) {
        this.clinicService = clinicService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<ClinicResource> getAllClinics(Pageable pageable) {
        return mapper.modelListToPage(clinicService.getAll(), pageable);
    }

    @GetMapping("{clinicId}")
    public ClinicResource getClinicById(@PathVariable Long clinicId) {
        return mapper.toResource(clinicService.getById(clinicId));
    }

    @PostMapping
    public ClinicResource createClinic(@RequestBody CreateClinicResource request) {
        return mapper.toResource(clinicService.create(mapper.toModel(request)));
    }

    @PutMapping("{clinicId}")
    public ClinicResource updateClinic(@PathVariable Long clinicId, @RequestBody UpdateClinicResource request) {
        return mapper.toResource(clinicService.update(clinicId, mapper.toModel(request)));
    }

    @DeleteMapping("{clinicId}")
    public ResponseEntity<?> delete(@PathVariable Long clinicId){
        return clinicService.delete(clinicId);
    }

}
