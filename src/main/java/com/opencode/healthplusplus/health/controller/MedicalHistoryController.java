package com.opencode.healthplusplus.health.controller;

import com.opencode.healthplusplus.health.domain.service.MedicalHistoryService;
import com.opencode.healthplusplus.health.mapping.MedicalHistoryMapper;
import com.opencode.healthplusplus.health.resource.CreateMedicalHistoryResource;
import com.opencode.healthplusplus.health.resource.MedicalHistoryResource;
import com.opencode.healthplusplus.health.resource.UpdateMedicalHistoryResource;
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
public class MedicalHistoryController {
    private final MedicalHistoryService medicalHistoryService;
    private final MedicalHistoryMapper mapper;

    public MedicalHistoryController(MedicalHistoryService medicalHistoryService, MedicalHistoryMapper mapper) {
        this.medicalHistoryService = medicalHistoryService;
        this.mapper = mapper;
    }

    // By Patient Id
    @Tag(name = "Patients")
    @Operation(summary = "Get medical history", description = "Get All Medical Histories.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Medical Histories found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = MedicalHistoryResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("patients/{patientId}/medicalHistories")
    public Page<MedicalHistoryResource> getAllMedicalHistoriesByPatientId(@PathVariable Long patientId, Pageable pageable) {
        return mapper.modelListToPage(medicalHistoryService.getAllByPatientId(patientId), pageable);
    }

    @Tag(name = "Patients")
    @Operation(summary = "Get a medical history by id", description = "Get A Medical History By Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Medical History found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = MedicalHistoryResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("patients/{patientId}/medicalHistories/{medicalHistoryId}")
    public MedicalHistoryResource getMedicalHistoryByIdAndPatientId(@PathVariable Long patientId, @PathVariable Long medicalHistoryId) {
        return mapper.toResource(medicalHistoryService.getByIdAndByPatientId(patientId, medicalHistoryId));
    }


    // By Clinic Id

    @Tag(name = "Clinics")
    @Operation(summary = "Get medical histories", description = "Get All Medical Histories.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Medical Histories found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = MedicalHistoryResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("clinics/{clinicId}/medicalHistories")
    public Page<MedicalHistoryResource> getAllMedicalHistoriesByClinicId(@PathVariable Long clinicId, Pageable pageable) {
        return mapper.modelListToPage(medicalHistoryService.getAllByPatientId(clinicId), pageable);
    }

    @Tag(name = "Clinics")
    @Operation(summary = "Get a medical history by id", description = "Get A Medical History By Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Medical History found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = MedicalHistoryResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("clinics/{clinicId}/medicalHistories/{medicalHistoryId}")
    public MedicalHistoryResource getMedicalHistoryByIdAndClinicId(@PathVariable Long clinicId, @PathVariable Long medicalHistoryId) {
        return mapper.toResource(medicalHistoryService.getByIdAndByClinicId(clinicId, medicalHistoryId));
    }

    @Tag(name = "Clinics")
    @Operation(summary = "Create a medical history", description = "Create A Medical History.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Medical History created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CreateMedicalHistoryResource.class
                                    )
                            )
                    }
            )
    })
    @PostMapping("clinics/{clinicId}/medicalHistories")
    public MedicalHistoryResource createMedicalHistory(@PathVariable Long clinicId, @RequestBody CreateMedicalHistoryResource request) {
        return mapper.toResource(medicalHistoryService.create(clinicId, mapper.toModel(request)));
    }

    @Tag(name = "Clinics")
    @Operation(summary = "Edit a medical history", description = "Edit A Medical History By Given An Id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Medical History edited",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UpdateMedicalHistoryResource.class
                                    )
                            )
                    }
            )
    })
    @PutMapping("clinics/{clinicId}/medicalHistories/{medicalHistoryId}")
    public MedicalHistoryResource updateMedicalHistory(@PathVariable Long clinicId, @PathVariable Long medicalHistoryId, @RequestBody UpdateMedicalHistoryResource request) {
        return mapper.toResource(medicalHistoryService.update(clinicId, medicalHistoryId, mapper.toModel(request)));
    }

    @Tag(name = "Clinics")
    @Operation(summary = "Delete a medical history", description = "Delete A Medical History By Given An Id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Medical History deleted"
            )
    })
    @DeleteMapping("clinics/{clinicId}/medicalHistories/{medicalHistoryId}")
    public ResponseEntity<?> deleteMedicalHistory(@PathVariable Long clinicId, @PathVariable Long medicalHistoryId) {
        return medicalHistoryService.delete(clinicId, medicalHistoryId);
    }

}
