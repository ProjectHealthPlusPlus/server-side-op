package com.opencode.healthplusplus.security.domain.service;

import com.opencode.healthplusplus.security.domain.model.entity.UserSec;
import com.opencode.healthplusplus.security.domain.service.communication.AuthenticateRequest;
import com.opencode.healthplusplus.security.domain.service.communication.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserSecService extends UserDetailsService {

    ResponseEntity<?> authenticate(AuthenticateRequest request);
    ResponseEntity<?> register(RegisterRequest request);
    List<UserSec> getAll();

}
