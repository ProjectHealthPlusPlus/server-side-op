package com.opencode.healthplusplus.meeting.resource;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class CreateClinicLocationResource {

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String address;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String capitalCity;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String country;
}
