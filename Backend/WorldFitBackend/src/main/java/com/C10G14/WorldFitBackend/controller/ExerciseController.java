package com.C10G14.WorldFitBackend.controller;

import com.C10G14.WorldFitBackend.dto.exercise.ExerciseRequestDto;
import com.C10G14.WorldFitBackend.dto.exercise.ExerciseResponseDto;
import com.C10G14.WorldFitBackend.dto.MessageResponse;
import com.C10G14.WorldFitBackend.service.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Operation(summary = "Get all exercises")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of exercises or a empty list",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseController.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN') or hasRole('COACH')")
    @GetMapping("")
    public ResponseEntity<List<ExerciseResponseDto>> getAllExercises() {
        List<ExerciseResponseDto> exercises = exerciseService.getAllExercises();
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }

    @Operation(summary = "Get one exercise by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "One exercise",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseController.class)) }),
            @ApiResponse(responseCode = "400", description = "Error: Exercise not found",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN') or hasRole('COACH')")
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseResponseDto> getExerciseById(@PathVariable Long id) {
        ExerciseResponseDto exercise = exerciseService.getExerciseById(id);
        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }

    @Operation(summary = "Create a new exercise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A new exercise",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseController.class)) }),
            @ApiResponse(responseCode = "400", description = """
                     Possible responses:
                     
                     - Title is required
                     - Media URL is not valid
                    """,
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Error: An exercise with that title already exists",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN') or hasRole('COACH')")
    @PostMapping("")
    public ResponseEntity<ExerciseResponseDto> createExercise(@RequestBody @Valid ExerciseRequestDto exercise) {
        ExerciseResponseDto createdExercise = exerciseService.createExercise(exercise);
        return new ResponseEntity<>(createdExercise, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing exercise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A updated exercise",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseController.class)) }),
            @ApiResponse(responseCode = "400", description = """
                     Possible responses:
                     
                     - Title is required
                     - Media URL is not valid
                    """,
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Error: An exercise with that title already exists",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN') or hasRole('COACH')")
    @PutMapping("/{id}")
    public ResponseEntity<ExerciseResponseDto> updateExercise(@PathVariable Long id,
                                                              @RequestBody @Valid ExerciseRequestDto exercise) {
        ExerciseResponseDto updatedExercise = exerciseService.updateExercise(id, exercise);
        return new ResponseEntity<>(updatedExercise, HttpStatus.OK);
    }

    @Operation(summary = "Delete a exercise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExerciseController.class)) }),
            @ApiResponse(responseCode = "400", description = "Error: Exercise not found",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN') or hasRole('COACH')")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return new ResponseEntity<>(new MessageResponse(200,"Exercise deleted"),
                HttpStatus.OK);
    }
}
