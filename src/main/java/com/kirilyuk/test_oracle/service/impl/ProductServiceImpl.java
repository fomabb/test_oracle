package com.kirilyuk.test_oracle.service.impl;

import com.kirilyuk.test_oracle.dao.OrdersDAO;
import com.kirilyuk.test_oracle.dao.ProductDAO;
import com.kirilyuk.test_oracle.entity.Goods;
import com.kirilyuk.test_oracle.entity.Orders;
import com.kirilyuk.test_oracle.service.ProductService;
import jakarta.persistence.EntityManager;
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
    private final EntityManager manager;

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

        if (goods.getQuantity() >= 1) {
            goods.setPrice(goods.getPrice() * goods.getQuantity());
        } else {
            deleteGoods(goods.getId());
            goods.setOrder(null);
//            manager.createNativeQuery("ALTER SEQUENCE goods_id_seq RESTART WITH 1").executeUpdate();
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

    @Override
    public List<Goods> getAllOrdersById(Long id) {

        return dao.getOrdersById(id);
    }

    @Override
    public List<Goods> search(String text) {

        return dao.search(text);
    }

    @Override
    public List<Orders> getOrdersInfo(Long id) {

        return ordersDao.getOrdersInfo(id);
    }

//    @Override
//    public List<Goods> findGoodsAll(Long id) {
//
//         dao.findAll().stream().count();
//         getGoodsById(id);
//         getOrderById(id);
//         ordersDao.countOrder(id);
//
//         return null;
//
//    }
}