package com.opencode.healthplusplus.profile.controller;

import com.opencode.healthplusplus.profile.domain.entity.Doctor;
import com.opencode.healthplusplus.profile.domain.service.DoctorService;
import com.opencode.healthplusplus.profile.mapping.DoctorMapper;
import com.opencode.healthplusplus.profile.resource.CreateDoctorResource;
import com.opencode.healthplusplus.profile.resource.DoctorResource;
import com.opencode.healthplusplus.profile.resource.UpdateDoctorResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Doctors")
@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final DoctorMapper mapper;

    public DoctorController(DoctorService doctorService, DoctorMapper mapper) {
        this.doctorService = doctorService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get doctors", description = "Get All Doctors.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Doctors found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = DoctorResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping
    public Page<DoctorResource> getAllDoctors(Pageable pageable) {
        return mapper.modelListToPage(doctorService.getAll(), pageable);
    }


    @Operation(summary = "Get a doctor by id", description = "Get A Doctor By Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Doctor found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = DoctorResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("{doctorId}")
    public DoctorResource getDoctorById(@PathVariable Long doctorId) {
        return mapper.toResource(doctorService.getById(doctorId));
    }


    @Operation(summary = "Create a doctor", description = "Create A Doctor.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Doctor created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CreateDoctorResource.class
                                    )
                            )
                    }
            )
    })
    @PostMapping
    public DoctorResource createDoctor(@RequestBody CreateDoctorResource request) {
        return mapper.toResource(doctorService.create(mapper.toModel(request)));
    }


    @Operation(summary = "Edit a doctor", description = "Edit A Doctor By Given An Id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Doctor edited",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UpdateDoctorResource.class
                                    )
                            )
                    }
            )
    })
    @PutMapping("{doctorId}")
    public DoctorResource updateDoctor(@PathVariable Long doctorId, @RequestBody UpdateDoctorResource request) {
        return mapper.toResource(doctorService.update(doctorId,mapper.toModel(request)));
    }


    @Operation(summary = "Add clinic")
    @PatchMapping("{doctorId}")
    public DoctorResource assignClinicToDoctor(@PathVariable Long doctorId, @RequestBody UpdateDoctorResource request) {
        return mapper.toResource(doctorService.addClinic(doctorId, request.getClinicId()));
    }

    @Operation(summary = "Remove clinic")
    @PatchMapping("{doctorId}/removeClinic")
    public DoctorResource removeClinicToDoctor(@PathVariable Long doctorId, @RequestBody UpdateDoctorResource request) {
        return mapper.toResource(doctorService.deleteClinic(doctorId, request.getClinicId()));
    }

    @Operation(summary = "Add Specialty")
    @PatchMapping("{doctorId}/addSpecialty")
    public DoctorResource assignSpecialtyToDoctor(@PathVariable Long doctorId, @RequestBody UpdateDoctorResource request) {
        return mapper.toResource(doctorService.addSpecialty(doctorId, request.getSpecialtyId()));
    }

    @Operation(summary = "Remove Specialty")
    @PatchMapping("{doctorId}/removeSpecialty")
    public DoctorResource removeSpecialtyToDoctor(@PathVariable Long doctorId, @RequestBody UpdateDoctorResource request) {
        return mapper.toResource(doctorService.deleteSpecialty(doctorId, request.getSpecialtyId()));
    }


    @Operation(summary = "Delete a doctor", description = "Delete A Doctor By Given An Id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Doctor deleted",
                    content = @Content(mediaType = "application/json")
            )
    })
    @DeleteMapping("{doctorId}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long doctorId) {
        return doctorService.delete(doctorId);
    }

}
