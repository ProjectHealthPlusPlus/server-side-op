package com.opencode.healthplusplus.meeting.mapping;

import com.opencode.healthplusplus.meeting.domain.entity.Clinic;
import com.opencode.healthplusplus.meeting.resource.ClinicResource;
import com.opencode.healthplusplus.meeting.resource.CreateClinicResource;
import com.opencode.healthplusplus.meeting.resource.UpdateClinicResource;
import com.opencode.healthplusplus.shared.mapping.EnhanceModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ClinicMapper implements Serializable {

    @Autowired
    private EnhanceModelMapper mapper;

     // Object Mapping

    public ClinicResource toResource(Clinic model) {
        return mapper.map(model, ClinicResource.class);
    }

    public Page<ClinicResource> modelListToPage(List<Clinic> modelList, Pageable pageable) {
        return new PageImpl<>(
                mapper.mapList(modelList, ClinicResource.class),
                pageable,
                modelList.size());
    }

    public Clinic toModel(CreateClinicResource resource) {
        return mapper.map(resource, Clinic.class);
    }

    public Clinic toModel(UpdateClinicResource resource) {
        return mapper.map(resource, Clinic.class);
    }

}
