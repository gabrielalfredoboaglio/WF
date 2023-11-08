package com.C10G14.WorldFitBackend.dto.exercise;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RemoveExerciseRequestDto {
    List<Long> exercises;
}
