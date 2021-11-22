package com.opencode.healthplusplus.profile.controller;

import com.opencode.healthplusplus.profile.domain.service.DoctorService;
import com.opencode.healthplusplus.profile.mapping.DoctorMapper;
import com.opencode.healthplusplus.profile.resource.CreateDoctorResource;
import com.opencode.healthplusplus.profile.resource.DoctorResource;
import com.opencode.healthplusplus.profile.resource.UpdateDoctorResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Doctors")
@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final DoctorMapper mapper;

    public DoctorController(DoctorService doctorService, DoctorMapper mapper) {
        this.doctorService = doctorService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get doctors", description = "Get All Doctors.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Doctors found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = DoctorResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping
    public Page<DoctorResource> getAllDoctors(Pageable pageable) {
        return mapper.modelListToPage(doctorService.getAll(), pageable);
    }


    @Operation(summary = "Get a doctor by id", description = "Get A Doctor By Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Doctor found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = DoctorResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("{doctorId}")
    public DoctorResource getDoctorById(@PathVariable Long doctorId) {
        return mapper.toResource(doctorService.getById(doctorId));
    }


    @Operation(summary = "Create a doctor", description = "Create A Doctor.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(value = "{\"dni\": 87654321, " +
                                    "\"name\": \"Diego\", " +
                                    "\"lastName\": \"Henriquez\", " +
                                    "\"age\": 20, " +
                                    "\"specialtiesId\": [ 1 ], " +
                                    "\"clinicsId\": [ 1 ]}")
                    }
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Doctor created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = DoctorResource.class
                                    )
                            )
                    }
            )
    })
    @PostMapping
    public DoctorResource createDoctor(@RequestBody CreateDoctorResource request) {
        return mapper.toResource(doctorService.create(mapper.toModel(request)));
    }


    @Operation(summary = "Edit a doctor", description = "Edit A Doctor By Given An Id.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(value = "{\"dni\": 87654321, " +
                                    "\"name\": \"Diego\", " +
                                    "\"lastName\": \"Henriquez\", " +
                                    "\"age\": 21, " +
                                    "\"specialtiesId\": [ 2 ], " +
                                    "\"clinicsId\": [ 2 ]}")
                    }
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Doctor edited",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = DoctorResource.class
                                    )
                            )
                    }
            )
    })
    @PutMapping("{doctorId}")
    public DoctorResource updateDoctor(@PathVariable Long doctorId, @RequestBody UpdateDoctorResource request) {
        return mapper.toResource(doctorService.update(doctorId,mapper.toModel(request)));
    }


    @Operation(summary = "Add clinics")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(value = "{\"clinicsId\": [ 1 ]}")
                    }
            )
    )
    @PatchMapping("{doctorId}/addClinics")
    public DoctorResource assignClinicsToDoctor(@PathVariable Long doctorId, @RequestBody UpdateDoctorResource request) {
        return mapper.toResource(doctorService.addClinics(doctorId, request.getClinicsId()));
    }

    @Operation(summary = "Remove clinics")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(value = "{\"clinicsId\": [ 1 ]}")
                    }
            )
    )
    @PatchMapping("{doctorId}/removeClinics")
    public DoctorResource removeClinicsToDoctor(@PathVariable Long doctorId, @RequestBody UpdateDoctorResource request) {
        return mapper.toResource(doctorService.removeClinics(doctorId, request.getClinicsId()));
    }

    @Operation(summary = "Add Specialties")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(value = "{\"specialtiesId\": [ 3, 4 ]}")
                    }
            )
    )
    @PatchMapping("{doctorId}/addSpecialties")
    public DoctorResource assignSpecialtiesToDoctor(@PathVariable Long doctorId, @RequestBody UpdateDoctorResource request) {
        return mapper.toResource(doctorService.addSpecialties(doctorId, request.getSpecialtiesId()));
    }

    @Operation(summary = "Remove Specialties")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(value = "{\"specialtiesId\": [ 4 ]}")
                    }
            )
    )
    @PatchMapping("{doctorId}/removeSpecialties")
    public DoctorResource removeSpecialtiesToDoctor(@PathVariable Long doctorId, @RequestBody UpdateDoctorResource request) {
        return mapper.toResource(doctorService.removeSpecialties(doctorId, request.getSpecialtiesId()));
    }


    @Operation(summary = "Delete a doctor", description = "Delete A Doctor By Given An Id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Doctor deleted",
                    content = @Content(mediaType = "application/json")
            )
    })
    @DeleteMapping("{doctorId}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long doctorId) {
        return doctorService.delete(doctorId);
    }

}
