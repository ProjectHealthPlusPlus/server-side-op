package com.opencode.healthplusplus.profile.domain.service;

import com.opencode.healthplusplus.profile.domain.entity.AdminClinic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminClinicService {
    List<AdminClinic> getAll();
    Page<AdminClinic> getAll(Pageable pageable);
    AdminClinic getById(Long adminClinicId);
    AdminClinic create(AdminClinic request);
    AdminClinic update(Long adminClinicId, AdminClinic request);
    AdminClinic addSpecialties(Long adminClinicId, List<Long> specialtiesId);
    AdminClinic removeSpecialties(Long adminClinicId, List<Long> specialtiesId);
    ResponseEntity<?> delete(Long adminClinicId);
}
