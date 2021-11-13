package com.opencode.healthplusplus.profile.resource;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class UpdatePatientResource extends UpdateUserResource{
    @NotNull
    @Size(max = 100)
    private String address;
}
