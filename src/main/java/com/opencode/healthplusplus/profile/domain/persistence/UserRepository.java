package com.opencode.healthplusplus.profile.domain.persistence;

import com.opencode.healthplusplus.profile.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}