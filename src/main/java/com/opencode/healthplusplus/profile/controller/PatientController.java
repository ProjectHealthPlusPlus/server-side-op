package com.opencode.healthplusplus.profile.controller;

import com.opencode.healthplusplus.profile.domain.service.PatientService;
import com.opencode.healthplusplus.profile.mapping.PatientMapper;
import com.opencode.healthplusplus.profile.resource.CreatePatientResource;
import com.opencode.healthplusplus.profile.resource.PatientResource;
import com.opencode.healthplusplus.profile.resource.UpdatePatientResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final PatientService patientService;
    private final PatientMapper mapper;

    public PatientController(PatientService patientService, PatientMapper mapper) {
        this.patientService = patientService;
        this.mapper = mapper;
    }


    @GetMapping
    public Page<PatientResource> getAllPatients(Pageable pageable) {
        return mapper.modelListToPage(patientService.getAll(), pageable);
    }

    @GetMapping("{patientId}")
    public PatientResource getPatientById(@PathVariable Long patientId) {
        return mapper.toResource(patientService.getById(patientId));
    }

    @PostMapping
    public PatientResource createUser(@RequestBody CreatePatientResource request) {
        return mapper.toResource(patientService.create(mapper.toModel(request)));
    }

    @PutMapping("{patientId}")
    public PatientResource updatePatient(@PathVariable Long patientId, @RequestBody UpdatePatientResource request) {
        return mapper.toResource(patientService.update(patientId,mapper.toModel(request)));
    }

    @DeleteMapping("{patientId}")
    public ResponseEntity<?> deletePatient(@PathVariable Long patientId) {
        return patientService.delete(patientId);
    }

}
