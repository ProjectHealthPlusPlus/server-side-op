package com.opencode.healthplusplus.meeting.resource;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
public class LocationResource {
    private Long id;
    private String address;
    private String city;
    private String country;
}
