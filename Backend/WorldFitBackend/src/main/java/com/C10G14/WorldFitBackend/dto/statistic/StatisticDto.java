package com.C10G14.WorldFitBackend.dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticDto {

    private Long id;
    private String date;
    private double totalIncome;
    private double totalOutcome;
}
