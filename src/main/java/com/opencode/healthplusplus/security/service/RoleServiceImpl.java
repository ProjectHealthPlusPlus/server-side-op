package com.opencode.healthplusplus.security.service;

import com.opencode.healthplusplus.security.domain.model.entity.Role;
import com.opencode.healthplusplus.security.domain.model.enumeration.Roles;
import com.opencode.healthplusplus.security.domain.persistence.RoleRepository;
import com.opencode.healthplusplus.security.domain.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    private static String[] DEFAULT_ROLES = {"ROLE_USER", "ROLE_ADMIN"};

    @Override
    public void seed() {
        Arrays.stream(DEFAULT_ROLES).forEach(name -> {
            Roles roleName = Roles.valueOf(name);
            if(!roleRepository.existsByName(roleName)) {
                roleRepository.save(new Role().withName(roleName));
            }
        });

    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

}
