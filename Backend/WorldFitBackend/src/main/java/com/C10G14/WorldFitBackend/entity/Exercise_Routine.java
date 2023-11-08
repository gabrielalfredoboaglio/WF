package com.C10G14.WorldFitBackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "Exercise_Routine")
@Table(name = "EXERCISE_ROUTINE")
public class Exercise_Routine {

    @EmbeddedId
    private Exercise_RoutineId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ExerciseId")
    private Exercise exercise;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("RoutineId")
    private Routine routine;

    private int quantity;

    private int series;

    private int repetitions;

    public Exercise_Routine(Exercise exercise, Routine routine, int quantity, int series, int repetitions) {
        this.exercise = exercise;
        this.routine = routine;
        this.quantity = quantity;
        this.series = series;
        this.repetitions = repetitions;
        this.id = new Exercise_RoutineId(exercise.getId(), routine.getId());
    }

}


