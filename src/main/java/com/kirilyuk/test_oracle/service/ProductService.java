package com.kirilyuk.test_oracle.service;

import com.kirilyuk.test_oracle.dto.QuantityUpdateDTO;
import com.kirilyuk.test_oracle.entity.Goods;
import com.kirilyuk.test_oracle.entity.Orders;

import java.util.List;
import java.util.Optional;

public interface ProductService {

//    ***************Goods***************

    void createNewProduct(List<Goods> goods);

    List<Goods> getAllGoods();

    Optional<Goods> getGoodsById(Long id);

    void deleteGoods(Long id);

//    ***************Orders***************

    void saveOrder(Orders orders);

    void addGoodsInOrder(Long orderId, Long goodsId);

    List<Orders> getOrdersTable();

    Optional<Orders> getOrderById(Long id);

    void deleteOrder(Long id);

    List<Goods> getAllOrdersById(Long id);

    List<Goods> search(String text);

    QuantityUpdateDTO updateQuantity(Long id, QuantityUpdateDTO quantity);

//    List<Goods> findGoodsAll(Long id);
}