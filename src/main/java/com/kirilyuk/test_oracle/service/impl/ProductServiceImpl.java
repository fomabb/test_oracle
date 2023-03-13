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

        goods.forEach(goods1 -> goods1.setQuantity(1));

        dao.saveAllAndFlush(goods);
    }

    @Override
    public void addGoodsInOrder(Long orderId, Long goodsId) {

        Orders orders = getOrderById(orderId).orElse(null);

        Goods goods = getGoodsById(goodsId).orElse(null);

        assert goods != null;
        if (goods.getQuantity() != 1) {
            goods.setQuantity(1);
        }

        assert orders != null;
        orders.addGoodsToOrder(goods);

        orders.setDocDate(LocalDateTime.now());

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

        Orders order = new Orders();

        if (goods.getQuantity() >= 1) {
            goods.setPrice(goods.getPrice() * goods.getQuantity());
        } else {
            deleteGoods(goods.getId());
            goods.setOrder(null);
        }

        dao.saveAndFlush(goods);
    }

    @Override
    public List<Goods> getAllGootsOrder() {

        return dao.getAllGootsOrder();
    }

    @Override
    public List<Goods> getAllGoods() {

        return dao.getAllGoods();
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
    public void deleteOrder(Long id) {

        ordersDao.deleteById(id);
    }
}