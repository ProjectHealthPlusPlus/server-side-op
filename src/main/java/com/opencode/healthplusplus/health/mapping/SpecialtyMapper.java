package com.opencode.healthplusplus.health.mapping;

import com.opencode.healthplusplus.health.domain.entity.Specialty;
import com.opencode.healthplusplus.health.resource.CreateSpecialtyResource;
import com.opencode.healthplusplus.health.resource.SpecialtyResource;
import com.opencode.healthplusplus.health.resource.UpdateSpecialtyResource;
import com.opencode.healthplusplus.shared.mapping.EnhanceModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class SpecialtyMapper implements Serializable {

    @Autowired
    private EnhanceModelMapper mapper;

    // Object Mapping

    public SpecialtyResource toResource(Specialty model) {
        return mapper.map(model, SpecialtyResource.class);
    }

    public Page<SpecialtyResource> modelListToPage(List<Specialty> modelList, Pageable pageable) {
        return new PageImpl<>(
                mapper.mapList(modelList, SpecialtyResource.class),
                pageable,
                modelList.size());
    }

    public Specialty toModel(CreateSpecialtyResource resource) {
        return mapper.map(resource, Specialty.class);
    }

    public Specialty toModel(UpdateSpecialtyResource resource) {
        return mapper.map(resource, Specialty.class);
    }
}
