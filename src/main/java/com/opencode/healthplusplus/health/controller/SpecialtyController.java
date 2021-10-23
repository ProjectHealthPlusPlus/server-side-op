package com.opencode.healthplusplus.health.controller;

import com.opencode.healthplusplus.health.domain.service.SpecialtyService;
import com.opencode.healthplusplus.health.mapping.SpecialtyMapper;
import com.opencode.healthplusplus.health.resource.CreateSpecialtyResource;
import com.opencode.healthplusplus.health.resource.SpecialtyResource;
import com.opencode.healthplusplus.health.resource.UpdateSpecialtyResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/specialties")
public class SpecialtyController {

    private final SpecialtyService specialtyService;
    private final SpecialtyMapper mapper;

    public SpecialtyController(SpecialtyService specialtyService, SpecialtyMapper mapper) {
        this.specialtyService = specialtyService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<SpecialtyResource> getAllSpecialties(Pageable pageable) {
        return mapper.modelListToPage(specialtyService.getAll(), pageable);
    }

    @GetMapping("{specialtyId}")
    public SpecialtyResource getSpecialtyById(@PathVariable Long specialtyId) {
        return mapper.toResource(specialtyService.getById(specialtyId));
    }

    @PostMapping
    public SpecialtyResource createSpecialty(@RequestBody CreateSpecialtyResource request) {
        return mapper.toResource(specialtyService.create(mapper.toModel(request)));
    }

    @PutMapping("{specialtyId}")
    public SpecialtyResource updateSpecialty(@PathVariable Long specialtyId, @RequestBody UpdateSpecialtyResource request) {
        return mapper.toResource(specialtyService.update(specialtyId, mapper.toModel(request)));
    }

    @DeleteMapping("{specialtyId}")
    public ResponseEntity<?> deleteSpecialty(@PathVariable Long specialtyId) {
        return specialtyService.delete(specialtyId);
    }

}
