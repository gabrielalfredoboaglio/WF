package com.C10G14.WorldFitBackend.dto.exercise;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseRequestDto {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "title is required")
    private String title;
    private String description;
    @URL(message = "media URL not valid")
    private String media;
    private String unit;

}