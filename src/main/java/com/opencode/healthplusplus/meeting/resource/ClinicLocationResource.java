package com.opencode.healthplusplus.meeting.resource;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class ClinicLocationResource {
    private Long id;
    private String address;
    private String capitalCity;
    private String country;
}
