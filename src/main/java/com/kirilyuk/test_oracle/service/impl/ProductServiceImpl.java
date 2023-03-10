package com.kirilyuk.test_oracle.service.impl;

import com.kirilyuk.test_oracle.dao.OrdersDAO;
import com.kirilyuk.test_oracle.dao.ProductDAO;
import com.kirilyuk.test_oracle.dto.QuantityUpdateDTO;
import com.kirilyuk.test_oracle.entity.Goods;
import com.kirilyuk.test_oracle.entity.Orders;
import com.kirilyuk.test_oracle.service.ProductService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductDAO dao;
    private final OrdersDAO ordersDao;
    private final EntityManager manager;


//    *******************************************************Goods******************************************************

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

        orders.addGoodsToDepartment(goods);

        orders.setDocDate(LocalDateTime.now());

        ordersDao.saveAndFlush(orders);
    }

    @Override
    public Optional<Goods> getGoodsById(Long id) {

        return dao.findById(id);
    }

    @Override

    public List<Goods> getAllOrdersById(Long id) {

        return dao.getAllOrdersById(id);
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
            manager.createNativeQuery("ALTER SEQUENCE goods_id_seq RESTART WITH 1").executeUpdate();
        }


        orders.setDocDate(LocalDateTime.now());

        ordersDao.saveAndFlush(orders);
    }

    @Override
    public List<Goods> getAllGoods() {

        return dao.getAllGoods();
    }

    @Override
    public List<Goods> search(String text) {

        return dao.search(text);
    }

    @Override
    public void deleteGoods(Long id) {

        dao.deleteById(id);
    }

//    *******************************************************Orders*****************************************************


    @Override
    public List<Double> registry(Long orderId) {

        double sumPrice = dao.sumPrice();
        double weight = (dao.countGoods(orderId) / dao.sumQuantity());

        List<Double> registryList = new ArrayList<>();
        registryList.add(sumPrice);
        registryList.add(weight);

        return registryList;
    }

    @Override

    public List<Orders> getOrdersTable() {

        return ordersDao.findAll();
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
    public QuantityUpdateDTO updateQuantity(Long id, QuantityUpdateDTO quantity) {

        Orders orders = new Orders();

        if (dao.findById(id).isPresent()) {
            Goods goods = dao.findById(id).get();

            goods.setPrice((goods.getPrice()) / goods.getQuantity());

            goods.setQuantity(quantity.getQuantity());

            if (goods.getQuantity() >= 1) {
                goods.setPrice(goods.getPrice() * goods.getQuantity());
            } else {
                orders.deleteToOrder(goods);

                goods.setOrder(null);
            }

            Goods good = dao.save(goods);
            return new QuantityUpdateDTO(good.getId(), good.getQuantity());
        }
        return quantity;

        ordersDao.deleteById(id);

    }
}
