package com.opencode.healthplusplus.health.controller;

import com.opencode.healthplusplus.health.domain.service.DiagnosticService;
import com.opencode.healthplusplus.health.mapping.DiagnosticMapper;
import com.opencode.healthplusplus.health.resource.CreateDiagnosticResource;
import com.opencode.healthplusplus.health.resource.DiagnosticResource;
import com.opencode.healthplusplus.health.resource.UpdateDiagnosticResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class DiagnosticController {
    private final DiagnosticService diagnosticService;
    private final DiagnosticMapper mapper;

    public DiagnosticController(DiagnosticService diagnosticService, DiagnosticMapper mapper) {
        this.diagnosticService = diagnosticService;
        this.mapper = mapper;
    }


    // By Patient Id

    @GetMapping("patients/{patientId}/medicalHistories/{medicalHistoryId}/diagnostics")
    public Page<DiagnosticResource> getAllDiagnosticsByMedicalHistoryIdAndPatientId(@PathVariable Long medicalHistoryId, Pageable pageable) {
        return mapper.modelListToPage(diagnosticService.getAllByMedicalHistoryId(medicalHistoryId), pageable);
    }

    @GetMapping("patients/{patientId}/medicalHistories/{medicalHistoryId}/diagnostics/{diagnosticId}")
    public DiagnosticResource getDiagnosticsByMedicalHistoryIdAndPatientId(@PathVariable Long medicalHistoryId, @PathVariable Long diagnosticId) {
        return mapper.toResource(diagnosticService.getByIdAndByMedicalHistoryId(medicalHistoryId, diagnosticId));
    }


    // By Clinic Id

    @GetMapping("clinics/{clinicId}/medicalHistories/{medicalHistoryId}/diagnostics")
    public Page<DiagnosticResource> getAllDiagnosticsByMedicalHistoryIdAndClinicId(@PathVariable Long medicalHistoryId, Pageable pageable) {
        return mapper.modelListToPage(diagnosticService.getAllByMedicalHistoryId(medicalHistoryId), pageable);
    }

    @GetMapping("clinics/{clinicId}/medicalHistories/{medicalHistoryId}/diagnostics/{diagnosticId}")
    public DiagnosticResource getDiagnosticsByMedicalHistoryIdAndClinicId(@PathVariable Long medicalHistoryId, @PathVariable Long diagnosticId) {
        return mapper.toResource(diagnosticService.getByIdAndByMedicalHistoryId(medicalHistoryId, diagnosticId));
    }

    @PostMapping("clinics/{clinicId}/medicalHistories/{medicalHistoryId}/diagnostics/{diagnosticId}")
    public DiagnosticResource createDiagnostic(@PathVariable Long medicalHistoryId, @RequestBody CreateDiagnosticResource request) {
        return mapper.toResource(diagnosticService.create(medicalHistoryId, mapper.toModel(request)));
    }

    @PutMapping("clinics/{clinicId}/medicalHistories/{medicalHistoryId}/diagnostics/{diagnosticId}")
    public DiagnosticResource updateDiagnostic(@PathVariable Long medicalHistoryId, @PathVariable Long diagnosticId, @RequestBody UpdateDiagnosticResource request) {
        return mapper.toResource(diagnosticService.update(medicalHistoryId, diagnosticId, mapper.toModel(request)));
    }

    @DeleteMapping("clinics/{clinicId}/medicalHistories/{medicalHistoryId}/diagnostics/{diagnosticId}")
    public ResponseEntity<?> deleteDiagnostic(@PathVariable Long medicalHistoryId, @PathVariable Long diagnosticId) {
        return diagnosticService.delete(medicalHistoryId, diagnosticId);
    }

}
