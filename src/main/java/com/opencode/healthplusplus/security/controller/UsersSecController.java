package com.opencode.healthplusplus.security.controller;

import com.opencode.healthplusplus.security.domain.service.UserSecService;
import com.opencode.healthplusplus.security.domain.service.communication.AuthenticateRequest;
import com.opencode.healthplusplus.security.domain.service.communication.RegisterRequest;
import com.opencode.healthplusplus.security.mapping.UserSecMapper;
import com.opencode.healthplusplus.security.resource.UserSecResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Users Security")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/usersSec")
public class UsersSecController {

    private final UserSecService userSecService;

    private final UserSecMapper mapper;


    public UsersSecController(UserSecService userSecService, UserSecMapper mapper) {
        this.userSecService = userSecService;
        this.mapper = mapper;
    }

    @PostMapping("/auth/sign-in")
    public ResponseEntity<?> authenticateUserSec(@Valid @RequestBody AuthenticateRequest request) {
        return userSecService.authenticate(request);
    }

    @PostMapping("/auth/sign-up")
    public ResponseEntity<?> registerUserSec(@Valid @RequestBody RegisterRequest request) {
        return userSecService.register(request);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUsersSec(Pageable pageable) {
        Page<UserSecResource> resources = mapper.modelListToPage(userSecService.getAll(), pageable);
        return ResponseEntity.ok(resources);
    }

}
