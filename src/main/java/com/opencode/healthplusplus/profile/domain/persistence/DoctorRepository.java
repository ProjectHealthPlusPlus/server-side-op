package com.opencode.healthplusplus.profile.domain.persistence;

import com.opencode.healthplusplus.profile.domain.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findByDni(int dni);
}
