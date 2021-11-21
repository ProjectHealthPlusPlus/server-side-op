package com.opencode.healthplusplus.profile.mapping;

import com.opencode.healthplusplus.meeting.domain.entity.Clinic;
import com.opencode.healthplusplus.meeting.domain.persistence.ClinicRepository;
import com.opencode.healthplusplus.profile.domain.entity.Doctor;
import com.opencode.healthplusplus.profile.domain.entity.Specialty;
import com.opencode.healthplusplus.profile.domain.persistence.SpecialtyRepository;
import com.opencode.healthplusplus.profile.resource.CreateDoctorResource;
import com.opencode.healthplusplus.profile.resource.DoctorResource;
import com.opencode.healthplusplus.profile.resource.UpdateDoctorResource;
import com.opencode.healthplusplus.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class DoctorMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    @Autowired
    private SpecialtyRepository specialtyRepository;
    @Autowired
    private ClinicRepository clinicRepository;

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
        List<Specialty> specialties = specialtyRepository.findAllById(resource.getSpecialtiesId());
        List<Clinic> clinics = clinicRepository.findAllById(resource.getClinicsId());

        Doctor doctor = new Doctor();

        doctor.setDni(resource.getDni());
        doctor.setName(resource.getName());
        doctor.setLastName(resource.getLastName());
        doctor.setAge(resource.getAge());

        doctor.setSpecialties(specialties);
        doctor.setClinics(clinics);

        return doctor;

    }

    public Doctor toModel(UpdateDoctorResource resource) {
        List<Specialty> specialties = specialtyRepository.findAllById(resource.getSpecialtiesId());
        List<Clinic> clinics = clinicRepository.findAllById(resource.getClinicsId());

        Doctor doctor = mapper.map(resource, Doctor.class);

        doctor.setClinics(clinics);
        doctor.setSpecialties(specialties);

        return doctor;
    }
}
