package com.opencode.healthplusplus.health.service;

import com.opencode.healthplusplus.health.domain.entity.User;
import com.opencode.healthplusplus.health.domain.persistence.UserRepository;
import com.opencode.healthplusplus.health.domain.service.UserService;
import com.opencode.healthplusplus.shared.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(Long userId, User request) {
        return userRepository.findById(userId).map(user -> {
           user.setAge(request.getAge());
            user.setName(request.getName());
            user.setLastName(request.getLastName());
            user.setDni(request.getDni());
            return userRepository.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("User", userId));
    }

    @Override
    public ResponseEntity<?> delete(Long userId) {
        return userRepository.findById(userId).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("USer", userId));
    }
}
