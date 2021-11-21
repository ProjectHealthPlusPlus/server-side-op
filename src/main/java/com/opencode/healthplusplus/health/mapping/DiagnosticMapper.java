package com.opencode.healthplusplus.health.mapping;

import com.opencode.healthplusplus.health.domain.entity.Diagnostic;
import com.opencode.healthplusplus.health.resource.CreateDiagnosticResource;
import com.opencode.healthplusplus.health.resource.DiagnosticResource;
import com.opencode.healthplusplus.health.resource.UpdateDiagnosticResource;
import com.opencode.healthplusplus.profile.domain.entity.Specialty;
import com.opencode.healthplusplus.profile.domain.persistence.SpecialtyRepository;
import com.opencode.healthplusplus.shared.exception.ResourceNotFoundException;
import com.opencode.healthplusplus.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class DiagnosticMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    @Autowired
    private SpecialtyRepository specialtyRepository;

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
        Specialty specialty = specialtyRepository.findById(resource.getSpecialtyId())
                .orElseThrow(() -> new ResourceNotFoundException("Specialty", resource.getSpecialtyId()));

        Diagnostic diagnostic = new Diagnostic();

        diagnostic.setDescription(resource.getDescription());
        diagnostic.setSpecialty(specialty);
        diagnostic.setPublishDate(resource.getPublishDate());

        return diagnostic;
    }

    public Diagnostic toModel(UpdateDiagnosticResource resource) {
        return mapper.map(resource, Diagnostic.class);
    }
}
