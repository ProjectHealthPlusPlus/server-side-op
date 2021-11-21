package com.opencode.healthplusplus.security.resource;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class UserSecResource {
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
}
