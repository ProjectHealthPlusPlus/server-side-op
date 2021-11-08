package com.opencode.healthplusplus.profile.resource;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class UserResource {
    protected Long id;
    private int dni;
    private String name;
    private String lastName;
    private int age;
}
