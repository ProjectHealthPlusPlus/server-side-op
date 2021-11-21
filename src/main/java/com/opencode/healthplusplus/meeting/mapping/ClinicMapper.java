package com.opencode.healthplusplus.meeting.mapping;

import com.opencode.healthplusplus.meeting.domain.entity.Clinic;
import com.opencode.healthplusplus.meeting.domain.entity.Location;
import com.opencode.healthplusplus.meeting.domain.persistence.LocationRepository;
import com.opencode.healthplusplus.meeting.resource.ClinicResource;
import com.opencode.healthplusplus.meeting.resource.CreateClinicResource;
import com.opencode.healthplusplus.meeting.resource.UpdateClinicResource;
import com.opencode.healthplusplus.profile.domain.entity.Doctor;
import com.opencode.healthplusplus.profile.domain.persistence.DoctorRepository;
import com.opencode.healthplusplus.shared.exception.ResourceNotFoundException;
import com.opencode.healthplusplus.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ClinicMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private DoctorRepository doctorRepository;

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
        List<Doctor> doctors = doctorRepository.findAllById(resource.getDoctorsId());
        Location location = locationRepository.findById(resource.getLocationId())
                .orElseThrow(() -> new ResourceNotFoundException("Location", resource.getLocationId()));

        Clinic clinic = mapper.map(resource, Clinic.class);

        clinic.setDoctors(doctors);
        clinic.setLocation(location);

        return clinic;
    }

}
