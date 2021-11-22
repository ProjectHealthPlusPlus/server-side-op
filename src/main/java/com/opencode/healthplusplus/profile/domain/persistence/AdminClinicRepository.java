package com.opencode.healthplusplus.profile.domain.persistence;

import com.opencode.healthplusplus.profile.domain.entity.AdminClinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminClinicRepository extends JpaRepository<AdminClinic, Long> {
    AdminClinic findByDni(int dni);
}
