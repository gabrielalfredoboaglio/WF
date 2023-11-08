package com.C10G14.WorldFitBackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Statistics")
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATE")
    private String date;

    @Column(name = "TOTAL_INCOME")
    private Double totalIncome;

    @Column(name = "TOTAL_OUTCOME")
    private Double totalOutcome;

    @PrePersist
    protected void onCreate() {
        this.date = ZonedDateTime.now(ZoneId.of("GMT-3")).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
    public Statistic (String date, Double totalIncome, Double totalOutcome) {
        this.date = date;
        this.totalIncome = totalIncome;
        this.totalOutcome = totalOutcome;
    }
}