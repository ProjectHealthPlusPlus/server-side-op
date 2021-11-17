package com.opencode.healthplusplus.profile.controller;

import com.opencode.healthplusplus.profile.domain.service.SpecialtyService;
import com.opencode.healthplusplus.profile.mapping.SpecialtyMapper;
import com.opencode.healthplusplus.profile.resource.CreateSpecialtyResource;
import com.opencode.healthplusplus.profile.resource.SpecialtyResource;
import com.opencode.healthplusplus.profile.resource.UpdateSpecialtyResource;
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

@Tag(name = "Specialties")
@RestController
@RequestMapping("/api/v1/specialties")
public class SpecialtyController {

    private final SpecialtyService specialtyService;
    private final SpecialtyMapper mapper;

    public SpecialtyController(SpecialtyService specialtyService, SpecialtyMapper mapper) {
        this.specialtyService = specialtyService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get specialties", description = "Get All Specialties.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Specialties found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = SpecialtyResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping
    public Page<SpecialtyResource> getAllSpecialties(Pageable pageable) {
        return mapper.modelListToPage(specialtyService.getAll(), pageable);
    }


    @Operation(summary = "Get a specialty by id", description = "Get A Specialty By Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Specialty found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = SpecialtyResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("{specialtyId}")
    public SpecialtyResource getSpecialtyById(@PathVariable Long specialtyId) {
        return mapper.toResource(specialtyService.getById(specialtyId));
    }


    @Operation(summary = "Create a specialty", description = "Specialty A Patient.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(value = "{\"name\": \"Cardiology\", " +
                                    "\"description\": \"The branch of medicine that deals with diseases and abnormalities of the heart.\"}")
                    }
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Specialty created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = SpecialtyResource.class
                                    )
                            )
                    }
            )
    })
    @PostMapping
    public SpecialtyResource createSpecialty(@RequestBody CreateSpecialtyResource request) {
        return mapper.toResource(specialtyService.create(mapper.toModel(request)));
    }


    @Operation(summary = "Edit a specialty", description = "Edit A Specialty By Given An Id.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(value = "{\"name\": \"Cardio\", \"description\": " +
                                    "\"The branch of medicine that deals with diseases and abnormalities of the heart.\"}")
                    }
            )
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Specialty edited",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = SpecialtyResource.class
                                    )
                            )
                    }
            )
    })
    @PutMapping("{specialtyId}")
    public SpecialtyResource updateSpecialty(@PathVariable Long specialtyId, @RequestBody UpdateSpecialtyResource request) {
        return mapper.toResource(specialtyService.update(specialtyId, mapper.toModel(request)));
    }


    @Operation(summary = "Delete a specialty", description = "Delete A Specialty By Given An Id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Specialty deleted"
            )
    })
    @DeleteMapping("{specialtyId}")
    public ResponseEntity<?> deleteSpecialty(@PathVariable Long specialtyId) {
        return specialtyService.delete(specialtyId);
    }

}
