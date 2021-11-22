package com.opencode.healthplusplus.profile.domain.persistence;

import com.opencode.healthplusplus.profile.domain.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByDni(int dni);
}
