package com.kirilyuk.test_oracle.service;

import com.kirilyuk.test_oracle.dto.OrdersRegistryDTO;
import com.kirilyuk.test_oracle.dto.QuantityUpdateDTO;
import com.kirilyuk.test_oracle.entity.Goods;
import com.kirilyuk.test_oracle.entity.Orders;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductService {


//    *******************************************************Goods******************************************************

    void createNewProduct(Set<Goods> goods);

    List<Goods> getAllGoods();

    Optional<Goods> getGoodsById(Long id);

    void deleteGoods(Long id);

    boolean isExist(String  good);

//    *******************************************************Orders*****************************************************

    void saveOrder(Orders orders);

    void addGoodsInOrder(Long orderId, Long goodsId);

    List<Orders> getOrdersTable();

    Optional<Orders> getOrderById(Long id);

    void deleteOrder(Long id);

    List<Goods> getAllOrdersById(Long id);

    List<Goods> search(String text);

    QuantityUpdateDTO updateQuantity(Long id, QuantityUpdateDTO quantity);

    OrdersRegistryDTO registry(Long orderId);

    void test(Goods goods);
}
