package com.opencode.healthplusplus.health.domain.persistence;

import com.opencode.healthplusplus.health.domain.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
}
