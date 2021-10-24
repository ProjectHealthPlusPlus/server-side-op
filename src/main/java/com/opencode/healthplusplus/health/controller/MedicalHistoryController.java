package com.opencode.healthplusplus.health.controller;

import com.opencode.healthplusplus.health.domain.service.MedicalHistoryService;
import com.opencode.healthplusplus.health.mapping.MedicalHistoryMapper;
import com.opencode.healthplusplus.health.resource.CreateMedicalHistoryResource;
import com.opencode.healthplusplus.health.resource.MedicalHistoryResource;
import com.opencode.healthplusplus.health.resource.UpdateMedicalHistoryResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class MedicalHistoryController {
    private final MedicalHistoryService medicalHistoryService;
    private final MedicalHistoryMapper mapper;

    public MedicalHistoryController(MedicalHistoryService medicalHistoryService, MedicalHistoryMapper mapper) {
        this.medicalHistoryService = medicalHistoryService;
        this.mapper = mapper;
    }

    // By Patient Id

    @GetMapping("patients/{patientId}/medicalHistories")
    public Page<MedicalHistoryResource> getAllMedicalHistoriesByPatientId(@PathVariable Long patientId, Pageable pageable) {
        return mapper.modelListToPage(medicalHistoryService.getAllByPatientId(patientId), pageable);
    }

    @GetMapping("patients/{patientId}/medicalHistories/{medicalHistoryId}")
    public MedicalHistoryResource getMedicalHistoryByIdAndPatientId(@PathVariable Long patientId, @PathVariable Long medicalHistoryId) {
        return mapper.toResource(medicalHistoryService.getByIdAndByPatientId(patientId, medicalHistoryId));
    }


    // By Clinic Id

    @GetMapping("clinics/{clinicId}/medicalHistories")
    public Page<MedicalHistoryResource> getAllMedicalHistoriesByClinicId(@PathVariable Long clinicId, Pageable pageable) {
        return mapper.modelListToPage(medicalHistoryService.getAllByPatientId(clinicId), pageable);
    }

    @GetMapping("clinics/{clinicId}/medicalHistories/{medicalHistoryId}")
    public MedicalHistoryResource getMedicalHistoryByIdAndClinicId(@PathVariable Long clinicId, @PathVariable Long medicalHistoryId) {
        return mapper.toResource(medicalHistoryService.getByIdAndByClinicId(clinicId, medicalHistoryId));
    }

    @PostMapping("clinics/{clinicId}/medicalHistories")
    public MedicalHistoryResource createMedicalHistory(@PathVariable Long clinicId, @RequestBody CreateMedicalHistoryResource request) {
        return mapper.toResource(medicalHistoryService.create(clinicId, mapper.toModel(request)));
    }

    @PutMapping("clinics/{clinicId}/medicalHistories/{medicalHistoryId}")
    public MedicalHistoryResource updateMedicalHistory(@PathVariable Long clinicId, @PathVariable Long medicalHistoryId, @RequestBody UpdateMedicalHistoryResource request) {
        return mapper.toResource(medicalHistoryService.update(clinicId, medicalHistoryId, mapper.toModel(request)));
    }

    @DeleteMapping("clinics/{clinicId}/medicalHistories/{medicalHistoryId}")
    public ResponseEntity<?> deleteMedicalHistory(@PathVariable Long clinicId, @PathVariable Long medicalHistoryId) {
        return medicalHistoryService.delete(clinicId, medicalHistoryId);
    }

}
