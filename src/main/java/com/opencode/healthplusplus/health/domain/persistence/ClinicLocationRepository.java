package com.opencode.healthplusplus.health.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicLocationRepository extends JpaRepository<ClinicLocation, Long> {
}
