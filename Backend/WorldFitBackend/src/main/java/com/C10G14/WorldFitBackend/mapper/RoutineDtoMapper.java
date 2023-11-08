package com.C10G14.WorldFitBackend.mapper;

import com.C10G14.WorldFitBackend.dto.exercise.Exercise_RoutineResponseDto;
import com.C10G14.WorldFitBackend.dto.routine.RoutineRequestDto;
import com.C10G14.WorldFitBackend.dto.routine.RoutineResponseDto;
import com.C10G14.WorldFitBackend.entity.Routine;
import com.C10G14.WorldFitBackend.entity.User;
import com.C10G14.WorldFitBackend.exception.NotFoundException;
import com.C10G14.WorldFitBackend.repository.UserRepository;
import com.C10G14.WorldFitBackend.util.DtoFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoutineDtoMapper {

    private final UserRepository userRepository;
    private final DtoFormatter formatter;

    public RoutineResponseDto EntityToDto (Routine routine) {
        Set<Exercise_RoutineResponseDto> exercises = routine.getExercises().stream().map(e ->
                        new Exercise_RoutineResponseDto(
                        e.getExercise(),e.getQuantity(),e.getSeries(),e.getRepetitions()))
                .collect(Collectors.toSet());

        return new RoutineResponseDto(
                routine.getId(),
                routine.getTitle(),
                exercises
        );
    }

    public Routine DtoToEntity (RoutineRequestDto routineDto) {
        User user = userRepository.findById(routineDto.getUserId())
                .orElseThrow(()-> new NotFoundException("User not found"));
        return new Routine(formatter.format(routineDto.getTitle()),user);
    }

}
