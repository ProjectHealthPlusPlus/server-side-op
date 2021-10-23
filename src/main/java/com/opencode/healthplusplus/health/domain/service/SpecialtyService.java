package com.opencode.healthplusplus.health.domain.service;

import com.opencode.healthplusplus.health.domain.entity.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SpecialtyService {
    List<Specialty> getAll();
    Page<Specialty> getAll(Pageable pageable);
    Specialty getById(Long specialtyId);
    Specialty create(Specialty request);
    Specialty update(Long specialtyId, Specialty request);
    ResponseEntity<?> delete(Long specialtyId);
}
