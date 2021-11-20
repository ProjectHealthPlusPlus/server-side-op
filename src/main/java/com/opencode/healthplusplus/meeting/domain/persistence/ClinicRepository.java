package com.opencode.healthplusplus.meeting.domain.persistence;

import com.opencode.healthplusplus.meeting.domain.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    Clinic findByLocation_Id(Long locationId);
}
