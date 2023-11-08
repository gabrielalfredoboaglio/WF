package com.C10G14.WorldFitBackend.repository;

import com.C10G14.WorldFitBackend.entity.Unit;
import com.C10G14.WorldFitBackend.enumeration.EUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnitRepository extends JpaRepository<Unit,Long> {

    Optional<Unit> findByName(EUnit unitName);

}
