package com.kirilyuk.test_oracle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersRegistryDTO {

    private Long id;

    private int quantity;

    private double price;
}
