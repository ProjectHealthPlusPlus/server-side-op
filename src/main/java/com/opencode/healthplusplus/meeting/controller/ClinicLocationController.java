package com.opencode.healthplusplus.meeting.controller;

import com.opencode.healthplusplus.meeting.domain.service.LocationService;
import com.opencode.healthplusplus.meeting.mapping.ClinicLocationMapper;
import com.opencode.healthplusplus.meeting.resource.ClinicLocationResource;
import com.opencode.healthplusplus.meeting.resource.CreateClinicLocationResource;
import com.opencode.healthplusplus.meeting.resource.UpdateClinicLocationResource;
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

@Tag(name = "Clinic Locations")
@RestController
@RequestMapping("/api/v1/clinicLocations")
public class ClinicLocationController {

    private final LocationService locationService;
    private final ClinicLocationMapper mapper;

    public ClinicLocationController(LocationService locationService, ClinicLocationMapper mapper) {
        this.locationService = locationService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get clinicLocations", description = "Get All ClinicLocations.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "ClinicLocations found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ClinicLocationResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping
    public Page<ClinicLocationResource> getAllClinicLocations(Pageable pageable) {
        return mapper.modelListToPage(locationService.getAll(), pageable);
    }


    @Operation(summary = "Get a clinicLocation by id", description = "Get A Location By Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Location found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ClinicLocationResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("{clinicLocationId}")
    public ClinicLocationResource getClinicLocationById(@PathVariable Long clinicLocationId) {
        return mapper.toResource(locationService.getById(clinicLocationId));
    }


    @Operation(summary = "Create a clinicLocation", description = "Create A Location.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Location created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CreateClinicLocationResource.class
                                    )
                            )
                    }
            )
    })
    @PostMapping
    public ClinicLocationResource createClinicLocation(@RequestBody CreateClinicLocationResource request) {
        return mapper.toResource(locationService.create(mapper.toModel(request)));
    }


    @Operation(summary = "Edit a clinicLocation", description = "Edit A Location By Given An Id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Location edited",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UpdateClinicLocationResource.class
                                    )
                            )
                    }
            )
    })
    @PutMapping("{clinicLocationId}")
    public ClinicLocationResource updateClinicLocation(@PathVariable Long clinicLocationId, @RequestBody UpdateClinicLocationResource request) {
        return mapper.toResource(locationService.update(clinicLocationId, mapper.toModel(request)));
    }


    @Operation(summary = "Delete a clinicLocation", description = "Delete A Location By Given An Id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Location deleted"
            )
    })
    @DeleteMapping("{clinicLocationId}")
    public ResponseEntity<?> deleteClinicLocation(@PathVariable Long clinicLocationId) {
        return locationService.delete(clinicLocationId);
    }

}
