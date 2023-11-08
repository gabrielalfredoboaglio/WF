package com.C10G14.WorldFitBackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Exercise_RoutineId implements Serializable {

    @Column(name = "EXERCISE_ID")
    private Long exerciseId;

    @Column(name = "ROUTINE_ID")
    private Long routineId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exercise_RoutineId that = (Exercise_RoutineId) o;
        return Objects.equals(exerciseId, that.exerciseId) && Objects.equals(routineId, that.routineId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exerciseId, routineId);
    }
}
