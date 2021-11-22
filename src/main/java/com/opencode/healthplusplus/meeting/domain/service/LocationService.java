package com.opencode.healthplusplus.meeting.domain.service;

import com.opencode.healthplusplus.meeting.domain.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LocationService {
    List<Location> getAll();
    Page<Location> getAll(Pageable pageable);
    Location getById(Long clinicLocationId);
    Location create(Location clinicLocation);
    Location update(Long clinicLocationId, Location request);
    ResponseEntity<?> delete(Long clinicLocationId);
}
