package com.kirilyuk.test_oracle.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kirilyuk.test_oracle.entity.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersRegistryDTO {

    private Long numberOrder;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime date;

    private List<Goods> numbersGoods = new ArrayList<>();

    private int quantityAllOrder;

    private double weightAllOrder;

    private double priceAllOrder;
}
