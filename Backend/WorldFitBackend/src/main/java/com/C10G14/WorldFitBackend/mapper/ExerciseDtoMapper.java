package com.C10G14.WorldFitBackend.mapper;

import com.C10G14.WorldFitBackend.dto.exercise.ExerciseRequestDto;
import com.C10G14.WorldFitBackend.dto.exercise.ExerciseResponseDto;
import com.C10G14.WorldFitBackend.entity.Exercise;
import com.C10G14.WorldFitBackend.entity.Unit;
import com.C10G14.WorldFitBackend.exception.NotFoundException;
import com.C10G14.WorldFitBackend.repository.UnitRepository;
import com.C10G14.WorldFitBackend.util.DtoFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExerciseDtoMapper {

     private final UnitRepository unitRepository;
     private final DtoFormatter formatter;

    public ExerciseResponseDto EntityToDto (Exercise exercise) {
        return new ExerciseResponseDto(
                exercise.getId(),
                exercise.getTitle(),
                exercise.getDescription(),
                exercise.getMedia(),
                exercise.getUnit().getName().toString()
        );
    }

    public Exercise DtoToEntity (ExerciseRequestDto exerciseDto) {
        return new Exercise(
                formatter.format(exerciseDto.getTitle()),
                formatter.format(exerciseDto.getDescription()),
                exerciseDto.getMedia(),
                unitRepository.findByName(Unit.UnitToEUnit(exerciseDto.getUnit()))
                        .orElseThrow(() ->
                                new NotFoundException("Unit must be either: 'Km', 'Kg', 'Minutos' or 'null'"))
        );
    }
}


