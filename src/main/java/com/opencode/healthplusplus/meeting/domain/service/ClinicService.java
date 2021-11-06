package com.opencode.healthplusplus.meeting.domain.service;

import com.opencode.healthplusplus.meeting.domain.entity.Clinic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClinicService {
    List<Clinic> getAll();
    Page<Clinic> getAll(Pageable pageable);
    Clinic getById(Long clinicId);
    Clinic create(Clinic request);
    Clinic update(Long clinicId, Clinic request);
    ResponseEntity<?> delete(Long clinicId);
}
