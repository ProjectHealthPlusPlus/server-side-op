package com.opencode.healthplusplus.security.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("securityMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public UserSecMapper userSecMapper() {
        return new UserSecMapper();
    }

    @Bean
    public RoleMapper roleMapper() {
        return new RoleMapper();
    }

}
