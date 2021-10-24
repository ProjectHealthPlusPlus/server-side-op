package com.opencode.healthplusplus.health.mapping;

import com.opencode.healthplusplus.health.domain.entity.Doctor;
import com.opencode.healthplusplus.health.resource.CreateDoctorResource;
import com.opencode.healthplusplus.health.resource.DoctorResource;
import com.opencode.healthplusplus.health.resource.UpdateDoctorResource;
import com.opencode.healthplusplus.shared.mapping.EnhanceModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class DoctorMapper implements Serializable {

    @Autowired
    private EnhanceModelMapper mapper;

    // Object Mapping

    public DoctorResource toResource(Doctor model) {
        return mapper.map(model, DoctorResource.class);
    }

    public Page<DoctorResource> modelListToPage(List<Doctor> modelList, Pageable pageable) {
        return new PageImpl<>(
                mapper.mapList(modelList, DoctorResource.class),
                pageable,
                modelList.size());
    }

    public Doctor toModel(CreateDoctorResource resource) {
        return mapper.map(resource, Doctor.class);
    }

    public Doctor toModel(UpdateDoctorResource resource) {
        return mapper.map(resource, Doctor.class);
    }
}
