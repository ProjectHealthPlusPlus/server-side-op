package com.opencode.healthplusplus.profile.controller;

import com.opencode.healthplusplus.profile.domain.service.PatientService;
import com.opencode.healthplusplus.profile.mapping.PatientMapper;
import com.opencode.healthplusplus.profile.resource.CreatePatientResource;
import com.opencode.healthplusplus.profile.resource.PatientResource;
import com.opencode.healthplusplus.profile.resource.UpdatePatientResource;
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

@Tag(name = "Patients")
@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final PatientService patientService;
    private final PatientMapper mapper;

    public PatientController(PatientService patientService, PatientMapper mapper) {
        this.patientService = patientService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get patients", description = "Get All Patients.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Patients found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = PatientResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping
    public Page<PatientResource> getAllPatients(Pageable pageable) {
        return mapper.modelListToPage(patientService.getAll(), pageable);
    }


    @Operation(summary = "Get a patient by id", description = "Get A Patient By Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Patient found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = PatientResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("{patientId}")
    public PatientResource getPatientById(@PathVariable Long patientId) {
        return mapper.toResource(patientService.getById(patientId));
    }


    @Operation(summary = "Create a patient", description = "Create A Patient.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Patient created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CreatePatientResource.class
                                    )
                            )
                    }
            )
    })
    @PostMapping
    public PatientResource createUser(@RequestBody CreatePatientResource request) {
        return mapper.toResource(patientService.create(mapper.toModel(request)));
    }


    @Operation(summary = "Edit a patient", description = "Edit A Patient By Given An Id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Patient edited",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UpdatePatientResource.class
                                    )
                            )
                    }
            )
    })
    @PutMapping("{patientId}")
    public PatientResource updatePatient(@PathVariable Long patientId, @RequestBody UpdatePatientResource request) {
        return mapper.toResource(patientService.update(patientId,mapper.toModel(request)));
    }


    @Operation(summary = "Delete a patient", description = "Delete A Patient By Given An Id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Patient deleted"
            )
    })
    @DeleteMapping("{patientId}")
    public ResponseEntity<?> deletePatient(@PathVariable Long patientId) {
        return patientService.delete(patientId);
    }

}
