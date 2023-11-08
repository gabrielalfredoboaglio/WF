package com.C10G14.WorldFitBackend.dto.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private Long id;
    private Double amount;
    private String currency;
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}


