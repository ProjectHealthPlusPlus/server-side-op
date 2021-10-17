package com.opencode.healthplusplus.health.domain.persistence;

import com.opencode.healthplusplus.health.domain.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
