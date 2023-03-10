package com.kirilyuk.test_oracle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersReportDTO {

    private Long id;

    private LocalDateTime date;

    private long quantity;

    private double price;
}
