package com.opencode.healthplusplus.health.domain.persistence;

import com.opencode.healthplusplus.health.domain.entity.MedicalHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {
    List<MedicalHistory> findByPatientId(Long patientId);
    List<MedicalHistory> findByClinicId(Long clinicId);

    Page<MedicalHistory> findByPatientId(Long patientId, Pageable pageable);
    Page<MedicalHistory> findByClinicId(Long clinicId, Pageable pageable);

    Optional<MedicalHistory> findByIdAndPatientId(Long id, Long patientId);
    Optional<MedicalHistory> findByIdAndClinicId(Long id, Long clinicId);
}
