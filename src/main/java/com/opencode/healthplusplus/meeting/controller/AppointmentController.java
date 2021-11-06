package com.opencode.healthplusplus.meeting.controller;

import com.opencode.healthplusplus.meeting.domain.service.AppointmentService;
import com.opencode.healthplusplus.meeting.mapping.AppointmentMapper;
import com.opencode.healthplusplus.meeting.resource.AppointmentResource;
import com.opencode.healthplusplus.meeting.resource.CreateAppointmentResource;
import com.opencode.healthplusplus.meeting.resource.UpdateAppointmentResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final AppointmentMapper mapper;

    public AppointmentController(AppointmentService appointmentService, AppointmentMapper mapper) {
        this.appointmentService = appointmentService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<AppointmentResource> getAllAppointmentDetails(Pageable pageable) {
        return mapper.modelListToPage(appointmentService.getAll(), pageable);
    }

    @GetMapping("{appointmentId}")
    public AppointmentResource getAppointmentDetailsById(@PathVariable Long appointmentId) {
        return mapper.toResource(appointmentService.getById(appointmentId));
    }

    @PostMapping
    public AppointmentResource createAppointmentDetails(@RequestBody CreateAppointmentResource request) {
        return mapper.toResource(appointmentService.create(mapper.toModel(request)));
    }

    @PutMapping("{appointmentId}")
    public AppointmentResource updateAppointmentDetails(@PathVariable Long appointmentId, @RequestBody UpdateAppointmentResource request) {
        return mapper.toResource(appointmentService.update(appointmentId,mapper.toModel(request)));
    }

    @DeleteMapping("{appointmentId}")
    public ResponseEntity<?> deleteAppointmentDetails(@PathVariable Long appointmentId) {
        return appointmentService.delete(appointmentId);
    }

}
