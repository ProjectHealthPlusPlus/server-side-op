package com.opencode.healthplusplus.meeting.service;

import com.opencode.healthplusplus.meeting.domain.entity.AppointmentDetails;
import com.opencode.healthplusplus.meeting.domain.persistence.AppointmentDetailsRepository;
import com.opencode.healthplusplus.meeting.domain.service.AppointmentDetailsService;
import com.opencode.healthplusplus.shared.exception.ResourceNotFoundException;
import com.opencode.healthplusplus.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class AppointmentDetailsServiceImpl implements AppointmentDetailsService {

    private static final String ENTITY = "AppointmentDetails";
    private final AppointmentDetailsRepository appointmentDetailsRepository;
    private final Validator validator;

    public AppointmentDetailsServiceImpl(AppointmentDetailsRepository appointmentDetailsRepository, Validator validator) {
        this.appointmentDetailsRepository = appointmentDetailsRepository;
        this.validator = validator;
    }


    @Override
    public List<AppointmentDetails> getAll() {
        return appointmentDetailsRepository.findAll();
    }

    @Override
    public Page<AppointmentDetails> getAll(Pageable pageable) {
        return appointmentDetailsRepository.findAll(pageable);
    }

    @Override
    public AppointmentDetails getById(Long appointmentDetailsId) {
        return appointmentDetailsRepository.findById(appointmentDetailsId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, appointmentDetailsId));
    }

    @Override
    public AppointmentDetails create(AppointmentDetails request) {
        Set<ConstraintViolation<AppointmentDetails>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return appointmentDetailsRepository.save(request);
    }

    @Override
    public AppointmentDetails update(Long appointmentDetailsId, AppointmentDetails request) {
        Set<ConstraintViolation<AppointmentDetails>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return appointmentDetailsRepository.findById(appointmentDetailsId).map(appointmentDetails ->
                        appointmentDetailsRepository.save(
                                appointmentDetails.withPatientStartedAt(request.getPatientStartedAt())
                                        .withDoctorStartedAt(request.getDoctorStartedAt())
                                        .withPatientEndedAt(request.getPatientEndedAt())
                                        .withDoctorEndedAt(request.getDoctorEndedAt())
                                        .withDiagnostic(request.getDiagnostic())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, appointmentDetailsId));
    }

    @Override
    public ResponseEntity<?> delete(Long appointmentDetailsId) {
        return appointmentDetailsRepository.findById(appointmentDetailsId).map(appointmentDetails -> {
            appointmentDetailsRepository.delete(appointmentDetails);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, appointmentDetailsId));
    }
}
