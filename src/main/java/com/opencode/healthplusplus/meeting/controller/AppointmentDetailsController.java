package com.opencode.healthplusplus.meeting.controller;

import com.opencode.healthplusplus.meeting.domain.service.AppointmentDetailsService;
import com.opencode.healthplusplus.meeting.mapping.AppointmentDetailsMapper;
import com.opencode.healthplusplus.meeting.resource.AppointmentDetailsResource;
import com.opencode.healthplusplus.meeting.resource.CreateAppointmentDetailsResource;
import com.opencode.healthplusplus.meeting.resource.UpdateAppointmentDetailsResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/appointmentDetails")
public class AppointmentDetailsController {

    private final AppointmentDetailsService appointmentDetailsService;
    private final AppointmentDetailsMapper mapper;

    public AppointmentDetailsController(AppointmentDetailsService appointmentDetailsService, AppointmentDetailsMapper mapper) {
        this.appointmentDetailsService = appointmentDetailsService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<AppointmentDetailsResource> getAllAppointmentDetails(Pageable pageable) {
        return mapper.modelListToPage(appointmentDetailsService.getAll(), pageable);
    }

    @GetMapping("{appointmentDetailsId}")
    public AppointmentDetailsResource getAppointmentDetailsById(@PathVariable Long appointmentDetailsId) {
        return mapper.toResource(appointmentDetailsService.getById(appointmentDetailsId));
    }

    @PostMapping
    public AppointmentDetailsResource createAppointmentDetails(@RequestBody CreateAppointmentDetailsResource request) {
        return mapper.toResource(appointmentDetailsService.create(mapper.toModel(request)));
    }

    @PutMapping("{appointmentDetailsId}")
    public AppointmentDetailsResource updateAppointmentDetails(@PathVariable Long appointmentDetailsId, @RequestBody UpdateAppointmentDetailsResource request) {
        return mapper.toResource(appointmentDetailsService.update(appointmentDetailsId,mapper.toModel(request)));
    }

    @DeleteMapping("{appointmentDetailsId}")
    public ResponseEntity<?> deleteAppointmentDetails(@PathVariable Long appointmentDetailsId) {
        return appointmentDetailsService.delete(appointmentDetailsId);
    }

}
