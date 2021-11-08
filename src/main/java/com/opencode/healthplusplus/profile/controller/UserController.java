package com.opencode.healthplusplus.profile.controller;

import com.opencode.healthplusplus.profile.domain.service.UserService;
import com.opencode.healthplusplus.profile.mapping.UserMapper;
import com.opencode.healthplusplus.profile.resource.CreateUserResource;
import com.opencode.healthplusplus.profile.resource.UpdateUserResource;
import com.opencode.healthplusplus.profile.resource.UserResource;
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

@Tag(name = "Users")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;

    public UserController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get users", description = "Get All Users.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Users found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UserResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping
    public Page<UserResource> getAllUsers(Pageable pageable) {
        return mapper.modelListToPage(userService.getAll(), pageable);
    }


    @Operation(summary = "Get a user by id", description = "Get A User By Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User found",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UserResource.class
                                    )
                            )
                    }
            )
    })
    @GetMapping("{userId}")
    public UserResource getUserById(@PathVariable Long userId) {
        return mapper.toResource(userService.getById(userId));
    }


    @Operation(summary = "Create a user", description = "Create A User.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User created",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = CreateUserResource.class
                                    )
                            )
                    }
            )
    })
    @PostMapping
    public UserResource createUser(@RequestBody CreateUserResource request) {
        return mapper.toResource(userService.create(mapper.toModel(request)));
    }


    @Operation(summary = "Edit a user", description = "Edit A User By Given An Id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User edited",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UpdateUserResource.class
                                    )
                            )
                    }
            )
    })
    @PutMapping("{userId}")
    public UserResource updateUser(@PathVariable Long userId, @RequestBody UpdateUserResource request) {
        return mapper.toResource(userService.update(userId,mapper.toModel(request)));
    }


    @Operation(summary = "Delete a user", description = "Delete A User By Given An Id.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User deleted"
            )
    })
    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userService.delete(userId);
    }

}
