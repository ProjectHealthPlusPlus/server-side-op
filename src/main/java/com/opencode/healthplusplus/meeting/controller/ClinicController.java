package com.opencode.healthplusplus.meeting.controller;

import com.opencode.healthplusplus.meeting.domain.service.ClinicService;
import com.opencode.healthplusplus.meeting.mapping.ClinicMapper;
import com.opencode.healthplusplus.meeting.resource.ClinicResource;
import com.opencode.healthplusplus.meeting.resource.CreateClinicResource;
import com.opencode.healthplusplus.meeting.resource.UpdateClinicResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(value = "{\"locationId\": 1, " +
                                    "\"doctorsId\": [ 1, 2 ]}")
                    }
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Clinic created",
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
    @PostMapping
    public ClinicResource createClinic(@RequestBody CreateClinicResource request) {
        return mapper.toResource(clinicService.create(mapper.toModel(request)));
    }


    @Operation(summary = "Edit a clinic", description = "Edit A Clinic By Given An Id.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(value = "{\"locationId\": 2, " +
                                    "\"doctorsId\": [ 2 ]}")
                    }
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Clinic edited",
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
    @PutMapping("{clinicId}")
    public ClinicResource updateClinic(@PathVariable Long clinicId, @RequestBody UpdateClinicResource request) {
        return mapper.toResource(clinicService.update(clinicId, mapper.toModel(request)));
    }


    @Operation(summary = "Change Location")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(value = "{\"locationId\": 2}")
                    }
            )
    )
    @PatchMapping("{clinicId}/changeLocation")
    public ClinicResource changeLocationOfClinic(@PathVariable Long clinicId, @RequestBody UpdateClinicResource request) {
        return mapper.toResource(clinicService.changeLocation(clinicId, request.getLocationId()));
    }

    @Operation(summary = "Add Doctors")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(value = "{\"doctorsId\": [ 1, 3 ]}")
                    }
            )
    )
    @PatchMapping("{clinicId}/addDoctors")
    public ClinicResource assignDoctorsToClinic(@PathVariable Long clinicId, @RequestBody UpdateClinicResource request) {
        return mapper.toResource(clinicService.addDoctors(clinicId, request.getDoctorsId()));
    }

    @Operation(summary = "Remove Doctors")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(value = "{\"doctorsId\": [ 2 ]}")
                    }
            )
    )
    @PatchMapping("{clinicId}/removeDoctors")
    public ClinicResource removeDoctorsToClinic(@PathVariable Long clinicId, @RequestBody UpdateClinicResource request) {
        return mapper.toResource(clinicService.removeDoctors(clinicId, request.getDoctorsId()));
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
