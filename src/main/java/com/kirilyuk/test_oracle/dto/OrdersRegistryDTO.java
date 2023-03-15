package com.kirilyuk.test_oracle.dto;

import com.kirilyuk.test_oracle.entity.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersRegistryDTO {

    private Long numberOrder;

    private List<Goods> numbersGoods = new ArrayList<>();

    private int quantityAllOrder;

    private double weightAllOrder;

    private double priceAllOrder;
}
