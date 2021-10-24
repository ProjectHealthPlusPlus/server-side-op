package com.opencode.healthplusplus.health.mapping;

import com.opencode.healthplusplus.health.domain.entity.Appointment;
import com.opencode.healthplusplus.health.resource.AppointmentResource;
import com.opencode.healthplusplus.health.resource.CreateAppointmentResource;
import com.opencode.healthplusplus.health.resource.UpdateAppointmentResource;
import com.opencode.healthplusplus.shared.mapping.EnhanceModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class AppointmentMapper {
    @Autowired
    private EnhanceModelMapper mapper;

    // Object Mapping

    public AppointmentResource toResource(Appointment model) {
        return mapper.map(model, AppointmentResource.class);
    }

    public Page<AppointmentResource> modelListToPage(List<Appointment> modelList, Pageable pageable) {
        return new PageImpl<>(
                mapper.mapList(modelList, AppointmentResource.class),
                pageable,
                modelList.size());
    }

    public Appointment toModel(CreateAppointmentResource resource) {
        return mapper.map(resource, Appointment.class);
    }

    public Appointment toModel(UpdateAppointmentResource resource) {
        return mapper.map(resource, Appointment.class);
    }
}
