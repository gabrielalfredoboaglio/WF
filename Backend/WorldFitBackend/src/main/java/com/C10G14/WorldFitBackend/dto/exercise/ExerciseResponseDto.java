package com.C10G14.WorldFitBackend.dto.exercise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseResponseDto {
    private long id;
    private String title;
    private String description;
    private String media;
    private String unit;

}
