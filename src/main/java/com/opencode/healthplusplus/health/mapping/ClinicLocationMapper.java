package com.opencode.healthplusplus.health.mapping;

import com.opencode.healthplusplus.health.domain.entity.ClinicLocation;
import com.opencode.healthplusplus.health.resource.ClinicLocationResource;
import com.opencode.healthplusplus.health.resource.CreateClinicLocationResource;
import com.opencode.healthplusplus.health.resource.UpdateClinicLocationResource;
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

    public ClinicLocationResource toResource(ClinicLocation model) {
        return mapper.map(model, ClinicLocationResource.class);
    }

    public Page<ClinicLocationResource> modelListToPage(List<ClinicLocation> modelList, Pageable pageable) {
        return new PageImpl<>(
                mapper.mapList(modelList, ClinicLocationResource.class),
                pageable,
                modelList.size());
    }

    public ClinicLocation toModel(CreateClinicLocationResource resource) {
        return mapper.map(resource, ClinicLocation.class);
    }

    public ClinicLocation toModel(UpdateClinicLocationResource resource) {
        return mapper.map(resource, ClinicLocation.class);
    }
}
