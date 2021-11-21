package com.opencode.healthplusplus.profile.mapping;

import com.opencode.healthplusplus.profile.domain.entity.Specialty;
import com.opencode.healthplusplus.profile.resource.CreateSpecialtyResource;
import com.opencode.healthplusplus.profile.resource.SpecialtyResource;
import com.opencode.healthplusplus.profile.resource.UpdateSpecialtyResource;
import com.opencode.healthplusplus.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class SpecialtyMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

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
