package com.opencode.healthplusplus.profile.domain.service;

import com.opencode.healthplusplus.profile.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<User> getAll();
    Page<User> getAll(Pageable pageable);
    User getById(Long userId);
    User create(User request);
    User update(Long userId, User request);
    ResponseEntity<?> delete(Long userId);
}
