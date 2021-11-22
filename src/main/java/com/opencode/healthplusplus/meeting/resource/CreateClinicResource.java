package com.opencode.healthplusplus.meeting.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateClinicResource {
    private Long locationId;
    private List<Long> doctorsId;
}
