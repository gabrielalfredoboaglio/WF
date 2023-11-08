package com.C10G14.WorldFitBackend.repository;

import com.C10G14.WorldFitBackend.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise,Long> {
    Boolean existsByTitle(String title);

}
