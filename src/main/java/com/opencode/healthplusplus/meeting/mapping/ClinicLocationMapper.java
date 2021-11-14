package com.opencode.healthplusplus.meeting.mapping;

import com.opencode.healthplusplus.meeting.domain.entity.Location;
import com.opencode.healthplusplus.meeting.resource.ClinicLocationResource;
import com.opencode.healthplusplus.meeting.resource.CreateClinicLocationResource;
import com.opencode.healthplusplus.meeting.resource.UpdateClinicLocationResource;
import com.opencode.healthplusplus.shared.mapping.EnhanceModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ClinicLocationMapper implements Serializable {

    @Autowired
    private EnhanceModelMapper mapper;

    // Object Mapping

    public ClinicLocationResource toResource(Location model) {
        return mapper.map(model, ClinicLocationResource.class);
    }

    public Page<ClinicLocationResource> modelListToPage(List<Location> modelList, Pageable pageable) {
        return new PageImpl<>(
                mapper.mapList(modelList, ClinicLocationResource.class),
                pageable,
                modelList.size());
    }

    public Location toModel(CreateClinicLocationResource resource) {
        return mapper.map(resource, Location.class);
    }

    public Location toModel(UpdateClinicLocationResource resource) {
        return mapper.map(resource, Location.class);
    }
}
