package com.opencode.healthplusplus.security.domain.service;

import com.opencode.healthplusplus.security.domain.model.entity.Role;

import java.util.List;

public interface RoleService {

    void seed();
    List<Role> getAll();

}
