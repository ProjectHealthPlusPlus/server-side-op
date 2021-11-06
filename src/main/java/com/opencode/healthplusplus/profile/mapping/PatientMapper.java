package com.opencode.healthplusplus.profile.mapping;

import com.opencode.healthplusplus.profile.domain.entity.Patient;

import com.opencode.healthplusplus.profile.resource.CreatePatientResource;
import com.opencode.healthplusplus.profile.resource.PatientResource;
import com.opencode.healthplusplus.profile.resource.UpdatePatientResource;
import com.opencode.healthplusplus.shared.mapping.EnhanceModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class PatientMapper implements Serializable {

    @Autowired
    private EnhanceModelMapper mapper;

    // Object Mapping

    public PatientResource toResource(Patient model) {
        return mapper.map(model, PatientResource.class);
    }

    public Page<PatientResource> modelListToPage(List<Patient> modelList, Pageable pageable) {
        return new PageImpl<>(
                mapper.mapList(modelList, PatientResource.class),
                pageable,
                modelList.size());
    }

    public Patient toModel(CreatePatientResource resource) {
        return mapper.map(resource, Patient.class);
    }

    public Patient toModel(UpdatePatientResource resource) {
        return mapper.map(resource, Patient.class);
    }
}
