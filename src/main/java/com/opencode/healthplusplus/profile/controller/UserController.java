package com.opencode.healthplusplus.profile.controller;

import com.opencode.healthplusplus.profile.domain.service.UserService;
import com.opencode.healthplusplus.profile.mapping.UserMapper;
import com.opencode.healthplusplus.profile.resource.CreateUserResource;
import com.opencode.healthplusplus.profile.resource.UpdateUserResource;
import com.opencode.healthplusplus.profile.resource.UserResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;

    public UserController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<UserResource> getAllUsers(Pageable pageable) {
        return mapper.modelListToPage(userService.getAll(), pageable);
    }

    @GetMapping("{userId}")
    public UserResource getUserById(@PathVariable Long userId) {
        return mapper.toResource(userService.getById(userId));
    }

    @PostMapping
    public UserResource createUser(@RequestBody CreateUserResource request) {
        return mapper.toResource(userService.create(mapper.toModel(request)));
    }

    @PutMapping("{userId}")
    public UserResource updateUser(@PathVariable Long userId, @RequestBody UpdateUserResource request) {
        return mapper.toResource(userService.update(userId,mapper.toModel(request)));
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userService.delete(userId);
    }

}
