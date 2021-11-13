package com.opencode.healthplusplus.meeting.controller;

import com.opencode.healthplusplus.meeting.domain.service.AppointmentDetailsService;
import com.opencode.healthplusplus.meeting.mapping.AppointmentDetailsMapper;
import com.opencode.healthplusplus.meeting.resource.AppointmentDetailsResource;
import com.opencode.healthplusplus.meeting.resource.CreateAppointmentDetailsResource;
import com.opencode.healthplusplus.meeting.resource.UpdateAppointmentDetailsResource;
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

@Tag(name = "AppointmentsDetails")
@RestController
@RequestMapping("/api/v1/appointmentDetails")
public class AppointmentDetailsController {

    private final AppointmentDetailsService appointmentDetailsService;
    private final AppointmentDetailsMapper mapper;

    public AppointmentDetailsController(AppointmentDetailsService appointmentDetailsService, AppointmentDetailsMapper mapper) {
        this.appointmentDetailsService = appointmentDetailsService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get appointmentsDetails", description = "Get All AppointmentsDetails.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "AppointmentDetails found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = AppointmentDetailsResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping
    public Page<AppointmentDetailsResource> getAllAppointmentDetails(Pageable pageable) {
        return mapper.modelListToPage(appointmentDetailsService.getAll(), pageable);
    }


    @Operation(summary = "Get an appointmentDetails by id", description = "Get A AppointmentDetails By Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "AppointmentDetails found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = AppointmentDetailsResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("{appointmentDetailsId}")
    public AppointmentDetailsResource getAppointmentDetailsById(@PathVariable Long appointmentDetailsId) {
        return mapper.toResource(appointmentDetailsService.getById(appointmentDetailsId));
    }


    @Operation(summary = "Create an appointmentDetails", description = "Create A AppointmentDetails.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "AppointmentDetails created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CreateAppointmentDetailsResource.class
                                    )
                            )
                    }
            )
    })
    @PostMapping
    public AppointmentDetailsResource createAppointmentDetails(@RequestBody CreateAppointmentDetailsResource request) {
        return mapper.toResource(appointmentDetailsService.create(mapper.toModel(request)));
    }


    @Operation(summary = "Edit an appointmentDetails", description = "Edit An AppointmentDetails By Given An Id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "AppointmentDetails edited",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UpdateAppointmentDetailsResource.class
                                    )
                            )
                    }
            )
    })
    @PutMapping("{appointmentDetailsId}")
    public AppointmentDetailsResource updateAppointmentDetails(@PathVariable Long appointmentDetailsId, @RequestBody UpdateAppointmentDetailsResource request) {
        return mapper.toResource(appointmentDetailsService.update(appointmentDetailsId,mapper.toModel(request)));
    }


    @Operation(summary = "Delete an appointmentDetails", description = "Delete An AppointmentDetails By Given An Id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "AppointmentDetails deleted"
            )
    })
    @DeleteMapping("{appointmentDetailsId}")
    public ResponseEntity<?> deleteAppointmentDetails(@PathVariable Long appointmentDetailsId) {
        return appointmentDetailsService.delete(appointmentDetailsId);
    }

}
