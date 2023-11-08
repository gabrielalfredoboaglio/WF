package com.C10G14.WorldFitBackend.service.impl;

import com.C10G14.WorldFitBackend.dto.exercise.ExerciseRequestDto;
import com.C10G14.WorldFitBackend.dto.exercise.ExerciseResponseDto;
import com.C10G14.WorldFitBackend.entity.Exercise;
import com.C10G14.WorldFitBackend.exception.AlreadyExistException;
import com.C10G14.WorldFitBackend.exception.NotFoundException;
import com.C10G14.WorldFitBackend.exception.SqlConstraintException;
import com.C10G14.WorldFitBackend.mapper.ExerciseDtoMapper;
import com.C10G14.WorldFitBackend.repository.ExerciseRepository;
import com.C10G14.WorldFitBackend.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseDtoMapper mapper;

    @Override
    public List<ExerciseResponseDto> getAllExercises() {
        List<Exercise> exercises = exerciseRepository.findAll();
        return exercises.stream().map(mapper::EntityToDto).toList();
    }

    @Override
    public ExerciseResponseDto getExerciseById(Long id) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(()->
                new NotFoundException("Exercise not found"));
        return mapper.EntityToDto(exercise);
    }

    @Override
    public ExerciseResponseDto createExercise(ExerciseRequestDto exerciseDto) {
        if(exerciseRepository.existsByTitle(exerciseDto.getTitle())){
            throw new AlreadyExistException("Error: An exercise with that title already exists: " + exerciseDto.getTitle());
        }
        Exercise exercise = mapper.DtoToEntity(exerciseDto);
        Exercise newExercise = exerciseRepository.save(exercise);
        return mapper.EntityToDto(newExercise);
    }

    @Override
    public ExerciseResponseDto updateExercise(Long id, ExerciseRequestDto exerciseDto) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Exercise not found"));
        Exercise updatedExercise = mapper.DtoToEntity(exerciseDto);
        updatedExercise.setId(exercise.getId());
        try {
            exerciseRepository.save(updatedExercise);
            return mapper.EntityToDto(updatedExercise);
        }catch (Exception e){
            throw new AlreadyExistException("An exercise with that title already exists: " + exerciseDto.getTitle());
        }
    }

    @Override
    public void deleteExercise(Long id) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(()->
                new NotFoundException("Exercise not found"));
        try {
            exerciseRepository.delete(exercise);
        }catch(Exception e){
            throw new SqlConstraintException("Integrity issue: the exercise is contained by routines");
        }
    }
}
