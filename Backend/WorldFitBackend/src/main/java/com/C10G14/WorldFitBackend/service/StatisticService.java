package com.C10G14.WorldFitBackend.service;

import com.C10G14.WorldFitBackend.dto.statistic.StatisticDateDto;
import com.C10G14.WorldFitBackend.dto.statistic.StatisticDto;

import java.util.List;

public interface StatisticService {

    StatisticDto save(StatisticDto statistic);

    List<StatisticDto> getAll();
    StatisticDto getById(Long id);
    List<StatisticDto> getByDate(StatisticDateDto date);
    List<StatisticDto> getByDateBetween(StatisticDateDto dates);
    void deleteById(Long id);
    void deleteByDateBetween(StatisticDateDto date);
}
