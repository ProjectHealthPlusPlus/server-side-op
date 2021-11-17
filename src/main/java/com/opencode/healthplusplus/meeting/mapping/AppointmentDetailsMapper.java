package com.opencode.healthplusplus.meeting.mapping;

import com.opencode.healthplusplus.health.domain.entity.Diagnostic;
import com.opencode.healthplusplus.health.domain.persistence.DiagnosticRepository;
import com.opencode.healthplusplus.meeting.domain.entity.AppointmentDetails;
import com.opencode.healthplusplus.meeting.resource.AppointmentDetailsResource;
import com.opencode.healthplusplus.meeting.resource.CreateAppointmentDetailsResource;
import com.opencode.healthplusplus.meeting.resource.UpdateAppointmentDetailsResource;
import com.opencode.healthplusplus.shared.exception.ResourceNotFoundException;
import com.opencode.healthplusplus.shared.mapping.EnhanceModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class AppointmentDetailsMapper {
    @Autowired
    private EnhanceModelMapper mapper;

    @Autowired
    private DiagnosticRepository diagnosticRepository;

    // Object Mapping

    public AppointmentDetailsResource toResource(AppointmentDetails model) {
        return mapper.map(model, AppointmentDetailsResource.class);
    }

    public Page<AppointmentDetailsResource> modelListToPage(List<AppointmentDetails> modelList, Pageable pageable) {
        return new PageImpl<>(
                mapper.mapList(modelList, AppointmentDetailsResource.class),
                pageable,
                modelList.size());
    }

    public AppointmentDetails toModel(CreateAppointmentDetailsResource resource) {

        Diagnostic diagnostic = diagnosticRepository.findById(resource.getDiagnosticId())
                .orElseThrow(() -> new ResourceNotFoundException("Diagnostic", resource.getDiagnosticId()));

        AppointmentDetails appointmentDetails = new AppointmentDetails();

        appointmentDetails.setDiagnostic(diagnostic);
        appointmentDetails.setPatientStartedAt(resource.getPatientStartedAt());
        appointmentDetails.setPatientEndedAt(resource.getPatientEndedAt());
        appointmentDetails.setDoctorStartedAt(resource.getDoctorStartedAt());
        appointmentDetails.setDoctorEndedAt(resource.getDoctorEndedAt());

        return appointmentDetails;
    }

    public AppointmentDetails toModel(UpdateAppointmentDetailsResource resource) {
        return mapper.map(resource, AppointmentDetails.class);
    }
}
