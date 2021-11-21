package com.opencode.healthplusplus.health.mapping;

import com.opencode.healthplusplus.health.domain.entity.MedicalHistory;
import com.opencode.healthplusplus.health.resource.CreateMedicalHistoryResource;
import com.opencode.healthplusplus.health.resource.MedicalHistoryResource;
import com.opencode.healthplusplus.health.resource.UpdateMedicalHistoryResource;
import com.opencode.healthplusplus.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class MedicalHistoryMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    // Object Mapping

    public MedicalHistoryResource toResource(MedicalHistory model) {
        return mapper.map(model, MedicalHistoryResource.class);
    }

    public Page<MedicalHistoryResource> modelListToPage(List<MedicalHistory> modelList, Pageable pageable) {
        return new PageImpl<>(
                mapper.mapList(modelList, MedicalHistoryResource.class),
                pageable,
                modelList.size());
    }

    public MedicalHistory toModel(CreateMedicalHistoryResource resource) {
        return mapper.map(resource, MedicalHistory.class);
    }

    public MedicalHistory toModel(UpdateMedicalHistoryResource resource) {
        return mapper.map(resource, MedicalHistory.class);
    }
}
