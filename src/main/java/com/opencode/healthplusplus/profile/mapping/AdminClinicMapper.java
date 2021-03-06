package com.opencode.healthplusplus.profile.mapping;

import com.opencode.healthplusplus.meeting.domain.entity.Clinic;
import com.opencode.healthplusplus.meeting.domain.persistence.ClinicRepository;
import com.opencode.healthplusplus.profile.domain.entity.AdminClinic;
import com.opencode.healthplusplus.profile.domain.entity.Specialty;
import com.opencode.healthplusplus.profile.domain.persistence.SpecialtyRepository;
import com.opencode.healthplusplus.profile.resource.AdminClinicResource;
import com.opencode.healthplusplus.profile.resource.CreateAdminClinicResource;
import com.opencode.healthplusplus.profile.resource.UpdateAdminClinicResource;
import com.opencode.healthplusplus.shared.exception.ResourceNotFoundException;
import com.opencode.healthplusplus.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class AdminClinicMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    @Autowired
    private SpecialtyRepository specialtyRepository;
    @Autowired
    private ClinicRepository clinicRepository;

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
        List<Specialty> specialties = specialtyRepository.findAllById(resource.getSpecialtiesId());
        Clinic clinic = clinicRepository.findById(resource.getClinicId())
                .orElseThrow(() -> new ResourceNotFoundException("Clinic", resource.getClinicId()));

        AdminClinic adminClinic = mapper.map(resource, AdminClinic.class);

        adminClinic.setSpecialties(specialties);
        adminClinic.setClinic(clinic);

        return adminClinic;
    }

    public AdminClinic toModel(UpdateAdminClinicResource resource) {
        List<Specialty> specialties = specialtyRepository.findAllById(resource.getSpecialtiesId());
        Clinic clinic = clinicRepository.findById(resource.getClinicId())
                .orElseThrow(() -> new ResourceNotFoundException("Clinic", resource.getClinicId()));

        AdminClinic adminClinic = mapper.map(resource, AdminClinic.class);

        adminClinic.setSpecialties(specialties);
        adminClinic.setClinic(clinic);

        return adminClinic;
    }

}
