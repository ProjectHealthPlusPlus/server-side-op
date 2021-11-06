package com.opencode.healthplusplus.meeting.domain.persistence;

import com.opencode.healthplusplus.meeting.domain.entity.AppointmentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentDetailsRepository extends JpaRepository<AppointmentDetails, Long> {
}
