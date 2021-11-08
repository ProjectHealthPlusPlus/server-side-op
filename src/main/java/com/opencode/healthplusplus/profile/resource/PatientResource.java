package com.opencode.healthplusplus.profile.resource;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class PatientResource extends UserResource{
    private String address;
}
