package com.opencode.healthplusplus.health.domain.service;

import com.opencode.healthplusplus.health.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<User> getAll();
    Page<User> getAll(Pageable pageable);
    User create(User user);
    User update(Long userId, User request);
    ResponseEntity<?> delete(Long userId);
}
