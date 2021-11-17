package com.opencode.healthplusplus.profile.controller;

import com.opencode.healthplusplus.profile.domain.service.AdminClinicService;
import com.opencode.healthplusplus.profile.mapping.AdminClinicMapper;
import com.opencode.healthplusplus.profile.resource.AdminClinicResource;
import com.opencode.healthplusplus.profile.resource.CreateAdminClinicResource;
import com.opencode.healthplusplus.profile.resource.UpdateAdminClinicResource;
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

@Tag(name = "Admins of Clinics")
@RestController
@RequestMapping("/api/v1/adminsClinics")
public class AdminClinicController {

    private final AdminClinicService adminClinicService;
    private final AdminClinicMapper mapper;

    public AdminClinicController(AdminClinicService adminClinicService, AdminClinicMapper mapper) {
        this.adminClinicService = adminClinicService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get admins of clinics", description = "Get All Admins of Clinics.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Admins Clinics found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = AdminClinicResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping
    public Page<AdminClinicResource> getAllDoctors(Pageable pageable) {
        return mapper.modelListToPage(adminClinicService.getAll(), pageable);
    }


    @Operation(summary = "Get an admin clinic", description = "Get An Admin Clinic")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Admin Clinic found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = AdminClinicResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("{adminClinicId}")
    public AdminClinicResource getDoctorById(@PathVariable Long adminClinicId) {
        return mapper.toResource(adminClinicService.getById(adminClinicId));
    }


    @Operation(summary = "Create an admin clinic", description = "Create An Admin Clinic.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Admin Clinic created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CreateAdminClinicResource.class
                                    )
                            )
                    }
            )
    })
    @PostMapping
    public AdminClinicResource createDoctor(@RequestBody CreateAdminClinicResource request) {
        return mapper.toResource(adminClinicService.create(mapper.toModel(request)));
    }


    @Operation(summary = "Edit an admin clinic", description = "Edit An Admin Clinic By Given An Id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Admin Clinic edited",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UpdateAdminClinicResource.class
                                    )
                            )
                    }
            )
    })
    @PutMapping("{adminClinicId}")
    public AdminClinicResource updateDoctor(@PathVariable Long adminClinicId, @RequestBody UpdateAdminClinicResource request) {
        return mapper.toResource(adminClinicService.update(adminClinicId,mapper.toModel(request)));
    }


    @Operation(summary = "Add Specialty")
    @PatchMapping("{adminClinicId}/addSpecialty")
    public AdminClinicResource assignSpecialtyToDoctor(@PathVariable Long adminClinicId, @RequestBody UpdateAdminClinicResource request) {
        return mapper.toResource(adminClinicService.addSpecialties(adminClinicId, request.getSpecialtiesId()));
    }

    @Operation(summary = "Remove Specialty")
    @PatchMapping("{adminClinicId}/removeSpecialty")
    public AdminClinicResource removeSpecialtyToDoctor(@PathVariable Long adminClinicId, @RequestBody UpdateAdminClinicResource request) {
        return mapper.toResource(adminClinicService.removeSpecialties(adminClinicId, request.getSpecialtiesId()));
    }


    @Operation(summary = "Delete a doctor", description = "Delete A Doctor By Given An Id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Doctor deleted",
                    content = @Content(mediaType = "application/json")
            )
    })
    @DeleteMapping("{adminClinicId}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long adminClinicId) {
        return adminClinicService.delete(adminClinicId);
    }


}
