package com.opencode.healthplusplus.meeting.domain.persistence;

import com.opencode.healthplusplus.meeting.domain.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
