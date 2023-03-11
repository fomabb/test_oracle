package com.kirilyuk.test_oracle.service;

import com.kirilyuk.test_oracle.entity.Goods;
import com.kirilyuk.test_oracle.entity.Orders;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void createNewProduct(List<Goods> goods);

    List<Goods> getAllProduct();

    List<Orders> getOrdersTable();

    Optional<Goods> getGoodsById(Long id);

    void update(Goods goods);

    Optional<Orders> getByIdOrders(Long id);

    double weight();

    List<Orders> getAllOrdersById(Long id);


    void saveOrders(Long id, Orders orders);

    boolean addToOrder(Long goodsId, Long orderId);
}
