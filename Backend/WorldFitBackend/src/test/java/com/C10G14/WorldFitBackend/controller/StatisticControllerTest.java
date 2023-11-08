package com.C10G14.WorldFitBackend.controller;

import com.C10G14.WorldFitBackend.dto.statistic.StatisticDto;
import com.C10G14.WorldFitBackend.entity.Statistic;
import com.C10G14.WorldFitBackend.service.StatisticService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StatisticControllerTest {

    @InjectMocks
    private StatisticController statisticController;

    @Mock
    private StatisticService statisticService;

    @Test
    public void createStatistic_ReturnsCreatedStatistic() {
        StatisticDto statisticDto = new StatisticDto();
        statisticDto.setTotalIncome(1000);
        statisticDto.setTotalOutcome(500);

        Statistic statistic = new Statistic();
        statistic.setId(1L);
        statistic.setTotalIncome(statisticDto.getTotalIncome());
        statistic.setTotalOutcome(statisticDto.getTotalOutcome());

        when(statisticService.save(any(StatisticDto.class))).thenReturn(statisticDto);

        ResponseEntity<StatisticDto> savedStatistic = statisticController.create(statisticDto);

        verify(statisticService).save(any(StatisticDto.class));

        ArgumentCaptor<StatisticDto> captor = ArgumentCaptor.forClass(StatisticDto.class);
        verify(statisticService).save(captor.capture());
        StatisticDto savedStatisticDto = captor.getValue();
        assertEquals(statistic.getTotalIncome(), savedStatisticDto.getTotalIncome());
        assertEquals(statistic.getTotalOutcome(), savedStatisticDto.getTotalOutcome());
    }
}




