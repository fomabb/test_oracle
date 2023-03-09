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

        goods.forEach(g -> g.setOrders(new Orders(LocalDateTime.now())));

//        goods.stream().map(goods1 -> new Orders(goods1.getId()))

        dao.saveAllAndFlush(goods);
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
    public void update(Goods goods) {

        goods.setPrice(goods.getPrice() * goods.getQuantity());

        dao.saveAndFlush(goods);
    }

    @Override
    public Optional<Orders> getByIdOrders(Long id) {

        return ordersDao.findById(id);
    }
}
