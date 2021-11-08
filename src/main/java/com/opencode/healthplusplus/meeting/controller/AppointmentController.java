package com.opencode.healthplusplus.meeting.controller;

import com.opencode.healthplusplus.meeting.domain.service.AppointmentService;
import com.opencode.healthplusplus.meeting.mapping.AppointmentMapper;
import com.opencode.healthplusplus.meeting.resource.AppointmentResource;
import com.opencode.healthplusplus.meeting.resource.CreateAppointmentResource;
import com.opencode.healthplusplus.meeting.resource.UpdateAppointmentResource;
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

@Tag(name = "Appointments")
@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final AppointmentMapper mapper;

    public AppointmentController(AppointmentService appointmentService, AppointmentMapper mapper) {
        this.appointmentService = appointmentService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get appointments", description = "Get All Appointments.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Appointments found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = AppointmentResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping
    public Page<AppointmentResource> getAllAppointmentDetails(Pageable pageable) {
        return mapper.modelListToPage(appointmentService.getAll(), pageable);
    }


    @Operation(summary = "Get an appointment by id", description = "Get An Appointment By Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Appointment found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = AppointmentResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("{appointmentId}")
    public AppointmentResource getAppointmentDetailsById(@PathVariable Long appointmentId) {
        return mapper.toResource(appointmentService.getById(appointmentId));
    }


    @Operation(summary = "Create an appointment", description = "Create An Appointment.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Appointment created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CreateAppointmentResource.class
                                    )
                            )
                    }
            )
    })
    @PostMapping
    public AppointmentResource createAppointmentDetails(@RequestBody CreateAppointmentResource request) {
        return mapper.toResource(appointmentService.create(mapper.toModel(request)));
    }


    @Operation(summary = "Edit an appointment", description = "Edit An Appointment By Given An Id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Appointment edited",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UpdateAppointmentResource.class
                                    )
                            )
                    }
            )
    })
    @PutMapping("{appointmentId}")
    public AppointmentResource updateAppointmentDetails(@PathVariable Long appointmentId, @RequestBody UpdateAppointmentResource request) {
        return mapper.toResource(appointmentService.update(appointmentId,mapper.toModel(request)));
    }


    @Operation(summary = "Delete an appointment", description = "Delete An Appointment By Given An Id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Appointment deleted"
            )
    })
    @DeleteMapping("{appointmentId}")
    public ResponseEntity<?> deleteAppointmentDetails(@PathVariable Long appointmentId) {
        return appointmentService.delete(appointmentId);
    }

}
