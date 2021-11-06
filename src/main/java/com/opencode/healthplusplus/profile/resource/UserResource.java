package com.opencode.healthplusplus.profile.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResource {
    protected Long id;
    private int dni;
    private String name;
    private String lastName;
    private int age;
}
