package com.opencode.healthplusplus.meeting.controller;

import com.opencode.healthplusplus.meeting.domain.service.ClinicService;
import com.opencode.healthplusplus.meeting.mapping.ClinicMapper;
import com.opencode.healthplusplus.meeting.resource.ClinicResource;
import com.opencode.healthplusplus.meeting.resource.CreateClinicResource;
import com.opencode.healthplusplus.meeting.resource.UpdateClinicResource;
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

@Tag(name = "Clinics")
@RestController
@RequestMapping("/api/v1/clinics")
public class ClinicController {

    private final ClinicService clinicService;
    private final ClinicMapper mapper;

    public ClinicController(ClinicService clinicService, ClinicMapper mapper) {
        this.clinicService = clinicService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get clinics", description = "Get All Clinics.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Clinics found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ClinicResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping
    public Page<ClinicResource> getAllClinics(Pageable pageable) {
        return mapper.modelListToPage(clinicService.getAll(), pageable);
    }


    @Operation(summary = "Get a clinic by id", description = "Get A Clinic By Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Clinic found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ClinicResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("{clinicId}")
    public ClinicResource getClinicById(@PathVariable Long clinicId) {
        return mapper.toResource(clinicService.getById(clinicId));
    }


    @Operation(summary = "Create a clinic", description = "Create A Clinic.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Clinic created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CreateClinicResource.class
                                    )
                            )
                    }
            )
    })
    @PostMapping
    public ClinicResource createClinic(@RequestBody CreateClinicResource request) {
        return mapper.toResource(clinicService.create(mapper.toModel(request)));
    }


    @Operation(summary = "Edit a clinic", description = "Edit A Clinic By Given An Id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Clinic edited",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UpdateClinicResource.class
                                    )
                            )
                    }
            )
    })
    @PutMapping("{clinicId}")
    public ClinicResource updateClinic(@PathVariable Long clinicId, @RequestBody UpdateClinicResource request) {
        return mapper.toResource(clinicService.update(clinicId, mapper.toModel(request)));
    }


    @Operation(summary = "Delete a clinic", description = "Delete A Clinic By Given An Id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Clinic deleted"
            )
    })
    @DeleteMapping("{clinicId}")
    public ResponseEntity<?> delete(@PathVariable Long clinicId){
        return clinicService.delete(clinicId);
    }

}
