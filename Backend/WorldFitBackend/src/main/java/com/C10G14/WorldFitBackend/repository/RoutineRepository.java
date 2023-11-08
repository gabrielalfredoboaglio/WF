package com.C10G14.WorldFitBackend.repository;

import com.C10G14.WorldFitBackend.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RoutineRepository extends JpaRepository<Routine, Long> {

    @Modifying
    @Transactional
    @Query(value = "delete from exercise_routine where routine_id=:rId and exercise_id=:eId",
            nativeQuery=true)
    void removeExercise(@Param("rId") long routineId,@Param("eId") long exerciseId);

    Boolean existsByTitleAndUser_Id(String title, Long userId);

}
