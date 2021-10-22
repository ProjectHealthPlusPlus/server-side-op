package com.opencode.healthplusplus.health.controller;

import com.opencode.healthplusplus.health.domain.entity.Clinic;
import com.opencode.healthplusplus.health.domain.service.ClinicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClinicController {

    private final ClinicService clinicService;

    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping("/clinics")
    public List<Clinic> getAll() {
        return clinicService.getAll();
    }

    @PostMapping("/clinics")
    public Clinic create(@Valid @RequestBody Clinic clinic) {
        return clinicService.create(clinic);
    }

    @PutMapping("/clinics/{clinicId}")
    public Clinic update(@PathVariable Long clinicId, @Valid @RequestBody Clinic request) {
        return clinicService.update(clinicId, request);
    }

    @DeleteMapping("/clinics/{clinicId}")
    public ResponseEntity<?> delete(@PathVariable Long clinicId){
        return clinicService.delete(clinicId);
    }

}
