package com.C10G14.WorldFitBackend.repository;

import com.C10G14.WorldFitBackend.entity.Statistic;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Statistic s WHERE s.date BETWEEN ?1 AND ?2")
    void deleteByDateBetween(String startDate, String endDate);

    @Query("SELECT s FROM Statistic s WHERE s.date = ?1")
    List<Statistic> getByDate(String date);

    @Query("SELECT s FROM Statistic s WHERE s.date BETWEEN ?1 AND ?2")
    List<Statistic> getByDateBetween(String startDate, String endDate);
}


