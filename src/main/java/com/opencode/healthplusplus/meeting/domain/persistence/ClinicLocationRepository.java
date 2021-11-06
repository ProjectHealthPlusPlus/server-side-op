package com.opencode.healthplusplus.meeting.domain.persistence;

import com.opencode.healthplusplus.meeting.domain.entity.ClinicLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicLocationRepository extends JpaRepository<ClinicLocation, Long> {
}
