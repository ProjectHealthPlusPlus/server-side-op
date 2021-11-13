package com.opencode.healthplusplus.profile.mapping;

import com.opencode.healthplusplus.profile.domain.entity.AdminClinic;
import com.opencode.healthplusplus.profile.resource.AdminClinicResource;
import com.opencode.healthplusplus.profile.resource.CreateAdminClinicResource;
import com.opencode.healthplusplus.profile.resource.UpdateAdminClinicResource;
import com.opencode.healthplusplus.shared.mapping.EnhanceModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class AdminClinicMapper implements Serializable {
    @Autowired
    private EnhanceModelMapper mapper;

    // Object Mapping

    public AdminClinicResource toResource(AdminClinic model) {
        return mapper.map(model, AdminClinicResource.class);
    }

    public Page<AdminClinicResource> modelListToPage(List<AdminClinic> modelList, Pageable pageable) {
        return new PageImpl<>(
                mapper.mapList(modelList, AdminClinicResource.class),
                pageable,
                modelList.size());
    }

    public AdminClinic toModel(CreateAdminClinicResource resource) {
        return mapper.map(resource, AdminClinic.class);
    }

    public AdminClinic toModel(UpdateAdminClinicResource resource) {
        return mapper.map(resource, AdminClinic.class);
    }

}
