package com.opencode.healthplusplus.health.controller;

import com.opencode.healthplusplus.health.domain.service.DoctorService;
import com.opencode.healthplusplus.health.mapping.DoctorMapper;
import com.opencode.healthplusplus.health.resource.CreateDoctorResource;
import com.opencode.healthplusplus.health.resource.DoctorResource;
import com.opencode.healthplusplus.health.resource.UpdateDoctorResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final DoctorMapper mapper;

    public DoctorController(DoctorService doctorService, DoctorMapper mapper) {
        this.doctorService = doctorService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<DoctorResource> getAllDoctors(Pageable pageable) {
        return mapper.modelListToPage(doctorService.getAll(), pageable);
    }

    @GetMapping("{doctorId}")
    public DoctorResource getDoctorById(@PathVariable Long doctorId) {
        return mapper.toResource(doctorService.getById(doctorId));
    }

    @PostMapping
    public DoctorResource createDoctor(@RequestBody CreateDoctorResource request) {
        return mapper.toResource(doctorService.create(mapper.toModel(request)));
    }

    @PutMapping("{doctorId}")
    public DoctorResource updateDoctor(@PathVariable Long doctorId, @RequestBody UpdateDoctorResource request) {
        return mapper.toResource(doctorService.update(doctorId,mapper.toModel(request)));
    }

    @DeleteMapping("{doctorId}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long doctorId) {
        return doctorService.delete(doctorId);
    }

}
