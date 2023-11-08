package com.C10G14.WorldFitBackend.service.impl;

import com.C10G14.WorldFitBackend.dto.exercise.Exercise_RoutineRequestDto;
import com.C10G14.WorldFitBackend.dto.exercise.RemoveExerciseRequestDto;
import com.C10G14.WorldFitBackend.dto.routine.RoutineRequestDto;
import com.C10G14.WorldFitBackend.dto.routine.RoutineResponseDto;
import com.C10G14.WorldFitBackend.entity.Exercise;
import com.C10G14.WorldFitBackend.entity.Routine;
import com.C10G14.WorldFitBackend.exception.AlreadyExistException;
import com.C10G14.WorldFitBackend.exception.NotFoundException;
import com.C10G14.WorldFitBackend.mapper.RoutineDtoMapper;
import com.C10G14.WorldFitBackend.repository.ExerciseRepository;
import com.C10G14.WorldFitBackend.repository.RoutineRepository;
import com.C10G14.WorldFitBackend.service.RoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoutineServiceImpl implements RoutineService {

    private final RoutineRepository routineRepository;
    private final ExerciseRepository exerciseRepository;
    private final RoutineDtoMapper mapper;

    @Override
    public RoutineResponseDto createRoutine(RoutineRequestDto routineDto) {
        if(routineRepository.existsByTitleAndUser_Id(
                routineDto.getTitle(), routineDto.getUserId())){
            throw new AlreadyExistException(
                    "The User already has routine with that name: " + routineDto.getTitle());
        }
        Routine routine = mapper.DtoToEntity(routineDto);
        Routine newRoutine = routineRepository.save(routine);
        return mapper.EntityToDto(newRoutine);
    }

    @Override
    public List<RoutineResponseDto> getAllRoutines() {
        List<Routine> routines = routineRepository.findAll();
        return routines.stream().map(mapper::EntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoutineResponseDto getRoutineById(long id) {
        Routine routine = routineRepository.findById(id).orElseThrow(()->
                new NotFoundException("Routine not found"));
        return mapper.EntityToDto(routine);
    }

    @Override
    public RoutineResponseDto updateRoutine(long id, RoutineRequestDto routineDto) {
        Routine routine = routineRepository.findById(id).orElseThrow(()->
                new NotFoundException("Routine not found"));
        routine.setTitle(routineDto.getTitle());
        Routine updatedRoutine = routineRepository.save(routine);
        return mapper.EntityToDto(updatedRoutine);
    }

    @Override
    public void deleteRoutine(long id) {
        Routine routine = routineRepository.findById(id).orElseThrow(()->
                new NotFoundException("Routine not found"));
        routineRepository.delete(routine);
    }

    public RoutineResponseDto addExercise(long routineId, Exercise_RoutineRequestDto e) {
        Routine routine = routineRepository.findById(routineId).orElseThrow(()->
                new NotFoundException("Routine not found"));
        Exercise exercise = exerciseRepository.findById(e.getExerciseId()).orElseThrow(()->
                new NotFoundException("Exercise not found"));

        routine.addExercise(exercise,e);
        Routine updatedRoutine = routineRepository.save(routine);

        return mapper.EntityToDto(updatedRoutine);
    }

    @Override
    public RoutineResponseDto updateExercise(long routineId, Exercise_RoutineRequestDto dto) {
        Routine routine = routineRepository.findById(routineId).orElseThrow(()->
                new NotFoundException("Routine not found"));
        Exercise exercise = exerciseRepository.findById(dto.getExerciseId()).orElseThrow(()->
                new NotFoundException("Exercise not found"));

        routine.updateExercise(exercise,dto);
        Routine updatedRoutine = routineRepository.save(routine);

        return mapper.EntityToDto(updatedRoutine);
    }

    @Override
    public RoutineResponseDto removeExercise(long routineId, RemoveExerciseRequestDto exercises) {
        Routine routine = routineRepository.findById(routineId)
                .orElseThrow(() -> new NotFoundException("Routine not found"));

        for (Long exerciseId : exercises.getExercises()) {
            Exercise exercise = exerciseRepository.findById(exerciseId)
                    .orElseThrow(() -> new NotFoundException("Exercise not found"));
            routine.removeExercise(exercise);
            routineRepository.removeExercise(routineId, exercise.getId());
        }

        Routine updatedRoutine = routineRepository.save(routine);
        return mapper.EntityToDto(updatedRoutine);
    }
}
