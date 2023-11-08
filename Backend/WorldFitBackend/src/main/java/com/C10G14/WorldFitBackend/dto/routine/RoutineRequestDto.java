package com.C10G14.WorldFitBackend.dto.routine;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutineRequestDto {
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "userId is required")
    private long userId;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "title is required")
    private String title;

}
