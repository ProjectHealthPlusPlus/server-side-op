package com.opencode.healthplusplus.meeting.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String address;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String city;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String country;
}
