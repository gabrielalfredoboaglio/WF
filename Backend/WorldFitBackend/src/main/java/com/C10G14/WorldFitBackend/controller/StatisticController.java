package com.C10G14.WorldFitBackend.controller;

import com.C10G14.WorldFitBackend.dto.statistic.StatisticDateDto;
import com.C10G14.WorldFitBackend.dto.statistic.StatisticDto;
import com.C10G14.WorldFitBackend.service.StatisticService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/statistics")
public class StatisticController {

    private final StatisticService statisticService;

    @Operation(summary = "Create new statistic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A new statistic",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StatisticController.class)) }),
            @ApiResponse(responseCode = "400", description = "Error: income and outcome must be a positive number",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<StatisticDto> create(@RequestBody StatisticDto statisticDto) {
        StatisticDto createdStatistic = statisticService.save(statisticDto);
        return new ResponseEntity<>(createdStatistic, HttpStatus.CREATED);
    }


    @Operation(summary = "Get all statistics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A list of statistics or a empty list",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StatisticController.class)) }),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)})
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<StatisticDto>> getAll() {
        List<StatisticDto> statisticDtoList = statisticService.getAll();
        return new ResponseEntity<>(statisticDtoList, HttpStatus.OK);
    }

    @Operation(summary = "Get a statistic by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A statistic",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StatisticController.class)) }),
            @ApiResponse(responseCode = "400", description = "Error: Statistic not found",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<StatisticDto> getById(@PathVariable Long id) {
        StatisticDto statisticDto = statisticService.getById(id);
        return new ResponseEntity<>(statisticDto, HttpStatus.OK);
    }

    @Operation(summary = "Get a statistic by date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A list of statistics or a empty list",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StatisticController.class)) }),
            @ApiResponse(responseCode = "400", description = "Error: Invalid date format. Please use the format dd-mm-yyyy",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/by-date")
    public ResponseEntity<List<StatisticDto>> getByDate(@RequestBody StatisticDateDto date) {
        List<StatisticDto> statisticDtoList = statisticService.getByDate(date);
        return new ResponseEntity<>(statisticDtoList, HttpStatus.OK);
    }

    @Operation(summary = "Get a statistic by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A list of statistics or a empty list",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StatisticController.class)) }),
            @ApiResponse(responseCode = "400", description = "Error: Invalid date format. Please use the format dd-mm-yyyy",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/by-dates-between")
    public ResponseEntity<List<StatisticDto>> getByDateBetween(@RequestBody StatisticDateDto dates) {
        List<StatisticDto> statisticDtoList = statisticService.getByDateBetween(dates);
        return new ResponseEntity<>(statisticDtoList, HttpStatus.OK);
    }

    @Operation(summary = "Delete a statistic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StatisticController.class)) }),
            @ApiResponse(responseCode = "400", description = "Error: Statistic not found",
            content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        statisticService.deleteById(id);
        return new ResponseEntity<>("Statistic successfully deleted", HttpStatus.OK);
    }

    @Operation(summary = "Delete a statistic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StatisticController.class)) }),
            @ApiResponse(responseCode = "400", description = "Error: Invalid date format. Please use the format dd-mm-yyyy",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/by-dates-between")
    public ResponseEntity<String> deleteByDate(@RequestBody StatisticDateDto dates) {
        statisticService.deleteByDateBetween(dates);
        return new ResponseEntity<>("Statistics successfully deleted", HttpStatus.OK);
    }
}
