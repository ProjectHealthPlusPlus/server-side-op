package com.opencode.healthplusplus.health.controller;

import com.opencode.healthplusplus.health.domain.service.DiagnosticService;
import com.opencode.healthplusplus.health.mapping.DiagnosticMapper;
import com.opencode.healthplusplus.health.resource.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Tag(name = "Patients")
    @Operation(summary = "Get diagnostics", description = "Get All Diagnostics.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Diagnostics found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = DiagnosticResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("patients/{patientId}/medicalHistories/{medicalHistoryId}/diagnostics")
    public Page<DiagnosticResource> getAllDiagnosticsByMedicalHistoryIdAndPatientId(@PathVariable Long medicalHistoryId, Pageable pageable) {
        return mapper.modelListToPage(diagnosticService.getAllByMedicalHistoryId(medicalHistoryId), pageable);
    }

    @Tag(name = "Patients")
    @Operation(summary = "Get a diagnostic by id", description = "Get A Diagnostic By Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Diagnostic found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = DiagnosticResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("patients/{patientId}/medicalHistories/{medicalHistoryId}/diagnostics/{diagnosticId}")
    public DiagnosticResource getDiagnosticsByMedicalHistoryIdAndPatientId(@PathVariable Long medicalHistoryId, @PathVariable Long diagnosticId) {
        return mapper.toResource(diagnosticService.getByIdAndByMedicalHistoryId(medicalHistoryId, diagnosticId));
    }


    // By Clinic Id
    @Tag(name = "Clinics")
    @Operation(summary = "Get diagnostics", description = "Get All Diagnostics.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Diagnostics found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = DiagnosticResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("clinics/{clinicId}/medicalHistories/{medicalHistoryId}/diagnostics")
    public Page<DiagnosticResource> getAllDiagnosticsByMedicalHistoryIdAndClinicId(@PathVariable Long medicalHistoryId, Pageable pageable) {
        return mapper.modelListToPage(diagnosticService.getAllByMedicalHistoryId(medicalHistoryId), pageable);
    }


    @Tag(name = "Clinics")
    @Operation(summary = "Get a diagnostic by id", description = "Get A Diagnostic By Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Diagnostic found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = DiagnosticResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("clinics/{clinicId}/medicalHistories/{medicalHistoryId}/diagnostics/{diagnosticId}")
    public DiagnosticResource getDiagnosticsByMedicalHistoryIdAndClinicId(@PathVariable Long medicalHistoryId, @PathVariable Long diagnosticId) {
        return mapper.toResource(diagnosticService.getByIdAndByMedicalHistoryId(medicalHistoryId, diagnosticId));
    }

    @Tag(name = "Clinics")
    @Operation(summary = "Create a diagnostic", description = "Create A Diagnostic.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Diagnostic created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CreateDiagnosticResource.class
                                    )
                            )
                    }
            )
    })
    @PostMapping("clinics/{clinicId}/medicalHistories/{medicalHistoryId}/diagnostics/")
    public DiagnosticResource createDiagnostic(@PathVariable Long medicalHistoryId, @RequestBody CreateDiagnosticResource request) {
        return mapper.toResource(diagnosticService.create(medicalHistoryId, mapper.toModel(request)));
    }

    @Tag(name = "Clinics")
    @Operation(summary = "Edit a diagnostic", description = "Edit A Diagnostic By Given An Id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Diagnostic edited",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UpdateDiagnosticResource.class
                                    )
                            )
                    }
            )
    })
    @PutMapping("clinics/{clinicId}/medicalHistories/{medicalHistoryId}/diagnostics/{diagnosticId}")
    public DiagnosticResource updateDiagnostic(@PathVariable Long medicalHistoryId, @PathVariable Long diagnosticId, @RequestBody UpdateDiagnosticResource request) {
        return mapper.toResource(diagnosticService.update(medicalHistoryId, diagnosticId, mapper.toModel(request)));
    }

    @Tag(name = "Clinics")
    @Operation(summary = "Delete a diagnostic", description = "Delete A Diagnostic By Given An Id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Diagnostic deleted"
            )
    })
    @DeleteMapping("clinics/{clinicId}/medicalHistories/{medicalHistoryId}/diagnostics/{diagnosticId}")
    public ResponseEntity<?> deleteDiagnostic(@PathVariable Long medicalHistoryId, @PathVariable Long diagnosticId) {
        return diagnosticService.delete(medicalHistoryId, diagnosticId);
    }

}
