package com.opencode.healthplusplus.profile.resource;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class UserResource {
    private Long id;
    private int dni;
    private String name;
    private String lastName;
    private int age;
}
