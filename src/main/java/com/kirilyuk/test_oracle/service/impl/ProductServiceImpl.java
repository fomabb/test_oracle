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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public void saveOrders(Long id, Orders orders) {

        getGoodsById(id).ifPresent(goods -> new Orders());

        orders.addGoodsToDepartment(getGoodsById(id).orElse(null));

        orders.setDocDate(LocalDateTime.now());

        ordersDao.saveAndFlush(orders);
    }

    @Override
    public void update(Goods goods) {

//        goods.setOrders(new Orders(LocalDateTime.now()));

        goods.setPrice(goods.getPrice() * goods.getQuantity());

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
    public Optional<Orders> getByIdOrders(Long id) {

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
