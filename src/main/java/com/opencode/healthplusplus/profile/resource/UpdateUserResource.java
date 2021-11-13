package com.opencode.healthplusplus.profile.resource;

import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class UpdateUserResource {

    protected Long id;

    @NotNull
    private int dni;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String lastName;

    @NotNull
    @Digits(integer = 3, fraction = 0)
    private int age;
}
