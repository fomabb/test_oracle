package com.kirilyuk.test_oracle.service.impl;

import com.kirilyuk.test_oracle.dao.OrdersDAO;
import com.kirilyuk.test_oracle.dao.ProductDAO;
import com.kirilyuk.test_oracle.entity.Goods;
import com.kirilyuk.test_oracle.entity.Orders;
import com.kirilyuk.test_oracle.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductDAO dao;
    private final OrdersDAO ordersDao;

    @Override
    public void createNewProduct(List<Goods> goods) {

        dao.saveAllAndFlush(goods);
    }

    @Override
    public void saveOrders(Long orderId, Long goodsId) {

        Orders orders = getOrderById(orderId).orElse(null);

        Goods goods = getGoodsById(goodsId).orElse(null);

        assert orders != null;
        orders.addGoodsToDepartment(goods);

        ordersDao.saveAndFlush(orders);
    }

    @Override
    public void deleteGoods(Long id) {

        dao.deleteById(id);
    }

    @Override
    public void saveOrder(Orders orders) {

        orders.setDocDate(LocalDateTime.now());

        ordersDao.saveAndFlush(orders);
    }

    @Override
    public void update(Goods goods) {

        goods.setPrice(goods.getPrice() * goods.getQuantity());

        if (goods.getQuantity() == 0) {
            deleteGoods(goods.getId());
        }

        dao.saveAndFlush(goods);
    }

    @Override
    public List<Goods> getAllProduct() {

        return dao.findAll();
    }

    @Override
    public List<Orders> getOrdersTable() {

        return ordersDao.findAll();
    }

    @Override
    public Optional<Goods> getGoodsById(Long id) {

        return dao.findById(id);
    }

    @Override
    public Optional<Orders> getOrderById(Long id) {

        return ordersDao.findById(id);
    }

    @Override
    public double weight() {

        return ordersDao.findAll().stream().count() / dao.findAll().stream().count();
    }

    @Override
    public List<Orders> getAllOrdersById(Long id) {

        return ordersDao.findAllById(Collections.singleton(id));
    }
}
