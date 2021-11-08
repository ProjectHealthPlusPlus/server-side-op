package com.opencode.healthplusplus.health.mapping;

import com.opencode.healthplusplus.health.domain.entity.Diagnostic;
import com.opencode.healthplusplus.health.resource.CreateDiagnosticResource;
import com.opencode.healthplusplus.health.resource.DiagnosticResource;
import com.opencode.healthplusplus.health.resource.UpdateDiagnosticResource;
import com.opencode.healthplusplus.shared.mapping.EnhanceModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class DiagnosticMapper implements Serializable {
    @Autowired
    private EnhanceModelMapper mapper;

    // Object Mapping

    public DiagnosticResource toResource(Diagnostic model) {
        return mapper.map(model, DiagnosticResource.class);
    }

    public Page<DiagnosticResource> modelListToPage(List<Diagnostic> modelList, Pageable pageable) {
        return new PageImpl<>(
                mapper.mapList(modelList, DiagnosticResource.class),
                pageable,
                modelList.size());
    }

    public Diagnostic toModel(CreateDiagnosticResource resource) {
        return mapper.map(resource, Diagnostic.class);
    }

    public Diagnostic toModel(UpdateDiagnosticResource resource) {
        return mapper.map(resource, Diagnostic.class);
    }
}
