package com.opencode.healthplusplus.meeting.mapping;

import com.opencode.healthplusplus.meeting.domain.entity.Appointment;
import com.opencode.healthplusplus.meeting.domain.entity.AppointmentDetails;
import com.opencode.healthplusplus.meeting.domain.persistence.AppointmentDetailsRepository;
import com.opencode.healthplusplus.meeting.resource.AppointmentResource;
import com.opencode.healthplusplus.meeting.resource.CreateAppointmentResource;
import com.opencode.healthplusplus.meeting.resource.UpdateAppointmentResource;
import com.opencode.healthplusplus.profile.domain.entity.Doctor;
import com.opencode.healthplusplus.profile.domain.entity.Patient;
import com.opencode.healthplusplus.profile.domain.persistence.DoctorRepository;
import com.opencode.healthplusplus.profile.domain.persistence.PatientRepository;
import com.opencode.healthplusplus.shared.exception.ResourceNotFoundException;
import com.opencode.healthplusplus.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class AppointmentMapper {
    @Autowired
    private EnhancedModelMapper mapper;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentDetailsRepository appointmentDetailsRepository;

    // Object Mapping

    public AppointmentResource toResource(Appointment model) {
        return mapper.map(model, AppointmentResource.class);
    }

    public Page<AppointmentResource> modelListToPage(List<Appointment> modelList, Pageable pageable) {
        return new PageImpl<>(
                mapper.mapList(modelList, AppointmentResource.class),
                pageable,
                modelList.size());
    }

    public Appointment toModel(CreateAppointmentResource resource) {

        Patient patient = patientRepository.findById(resource.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient", resource.getPatientId()));

        Doctor doctor = doctorRepository.findById(resource.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", resource.getDoctorId()));

        AppointmentDetails appointmentDetails = appointmentDetailsRepository.findById(resource.getAppointmentDetailsId())
                .orElse(null);

        Appointment appointment = new Appointment();

        appointment.setAppointmentDetails(appointmentDetails);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setStartAt(resource.getStartAt());

        return appointment;
    }

    public Appointment toModel(UpdateAppointmentResource resource) {
        return mapper.map(resource, Appointment.class);
    }
}
