package com.opencode.healthplusplus.health.domain.persistence;

import com.opencode.healthplusplus.health.domain.entity.Diagnostic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosticRepository extends JpaRepository<Diagnostic, Long> {
}
