package com.opencode.healthplusplus.meeting.domain.service;

import com.opencode.healthplusplus.meeting.domain.entity.ClinicLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClinicLocationService {
    List<ClinicLocation> getAll();
    Page<ClinicLocation> getAll(Pageable pageable);
    ClinicLocation getById(Long clinicLocationId);
    ClinicLocation create(ClinicLocation clinicLocation);
    ClinicLocation update(Long clinicLocationId, ClinicLocation request);
    ResponseEntity<?> delete(Long clinicLocationId);
}
