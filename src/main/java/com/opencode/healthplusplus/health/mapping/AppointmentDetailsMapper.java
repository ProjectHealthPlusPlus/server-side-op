package com.opencode.healthplusplus.health.mapping;

import com.opencode.healthplusplus.health.domain.entity.AppointmentDetails;
import com.opencode.healthplusplus.health.resource.AppointmentDetailsResource;
import com.opencode.healthplusplus.health.resource.CreateAppointmentDetailsResource;
import com.opencode.healthplusplus.health.resource.UpdateAppointmentDetailsResource;
import com.opencode.healthplusplus.shared.mapping.EnhanceModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class AppointmentDetailsMapper {
    @Autowired
    private EnhanceModelMapper mapper;

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
        return mapper.map(resource, AppointmentDetails.class);
    }

    public AppointmentDetails toModel(UpdateAppointmentDetailsResource resource) {
        return mapper.map(resource, AppointmentDetails.class);
    }
}
