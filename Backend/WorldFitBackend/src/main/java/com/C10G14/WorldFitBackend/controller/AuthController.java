package com.C10G14.WorldFitBackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.C10G14.WorldFitBackend.dto.user.AuthenticationRequestDto;
import com.C10G14.WorldFitBackend.dto.user.AuthenticationResponseDto;
import com.C10G14.WorldFitBackend.dto.user.RegisterRequestDto;
import com.C10G14.WorldFitBackend.service.impl.AuthServiceImpl;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;

    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JWT token returned",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthController.class)) }),
            @ApiResponse(responseCode = "400", description = """
                      Possible Responses:
                     
                    - Email is required
                    - Email direction isn't valid
                    - Password is required
                    - Password must contain at least 8 characters including letters, numbers, spaces and commas
                    - Weight must be greater than or equal to zero
                    - Weight must have two or fewer decimal places
                    - Height must be greater than or equal to zero
                    - Height must have two or fewer decimal places
                    - Sex must be either male or female (no case sensitive)
                    - Maximum age is 110
                    """,
                    content = @Content)})
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@ModelAttribute @Valid
                                              RegisterRequestDto request) {
        AuthenticationResponseDto registerResponse = authService.register(request);
        return new ResponseEntity<>(registerResponse, HttpStatus.OK);
    }

    @Operation(summary = "Authenticate an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "JWT token returned",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthController.class)) }),
            @ApiResponse(responseCode = "400", description = """
                      Possible Responses:
                    - Email is required
                    - Password is required
                    """,
                    content = @Content)})
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticateUser(
                                            @RequestBody AuthenticationRequestDto request) {
        AuthenticationResponseDto authResponse = authService.authenticate(request);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}