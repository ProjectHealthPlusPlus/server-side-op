package com.opencode.healthplusplus.meeting.controller;

import com.opencode.healthplusplus.meeting.domain.service.LocationService;
import com.opencode.healthplusplus.meeting.mapping.LocationMapper;
import com.opencode.healthplusplus.meeting.resource.LocationResource;
import com.opencode.healthplusplus.meeting.resource.CreateLocationResource;
import com.opencode.healthplusplus.meeting.resource.UpdateLocationResource;
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

@Tag(name = "Locations")
@RestController
@RequestMapping("/api/v1/clinicLocations")
public class LocationController {

    private final LocationService locationService;
    private final LocationMapper mapper;

    public LocationController(LocationService locationService, LocationMapper mapper) {
        this.locationService = locationService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get locations", description = "Get All Locations.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Locations found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = LocationResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping
    public Page<LocationResource> getAllClinicLocations(Pageable pageable) {
        return mapper.modelListToPage(locationService.getAll(), pageable);
    }


    @Operation(summary = "Get a Location by id", description = "Get A Location By Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Location found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = LocationResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("{clinicLocationId}")
    public LocationResource getClinicLocationById(@PathVariable Long clinicLocationId) {
        return mapper.toResource(locationService.getById(clinicLocationId));
    }


    @Operation(summary = "Create a Location", description = "Create A Location.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(value = "{\"address\": \"Av. Javier Prado\", " +
                                    "\"city\": \"Lima\", " +
                                    "\"country\": \"Peru\"}")
                    }
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Location created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = LocationResource.class
                                    )
                            )
                    }
            )
    })
    @PostMapping
    public LocationResource createClinicLocation(@RequestBody CreateLocationResource request) {
        return mapper.toResource(locationService.create(mapper.toModel(request)));
    }


    @Operation(summary = "Edit a Location", description = "Edit A Location By Given An Id.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(value = "{\"address\": \"Av. Arequipa\", " +
                                    "\"city\": \"Ica\", " +
                                    "\"country\": \"Peru\"}")
                    }
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Location edited",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = LocationResource.class
                                    )
                            )
                    }
            )
    })
    @PutMapping("{clinicLocationId}")
    public LocationResource updateClinicLocation(@PathVariable Long clinicLocationId, @RequestBody UpdateLocationResource request) {
        return mapper.toResource(locationService.update(clinicLocationId, mapper.toModel(request)));
    }


    @Operation(summary = "Delete a Location", description = "Delete A Location By Given An Id.")
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
