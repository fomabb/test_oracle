package com.kirilyuk.test_oracle.service.impl;

import com.kirilyuk.test_oracle.dao.ProductDAO;
import com.kirilyuk.test_oracle.entity.Goods;
import com.kirilyuk.test_oracle.entity.Orders;
import com.kirilyuk.test_oracle.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductDAO dao;

    @Override
    public void createNewProduct(Goods goods) {

        goods.setOrders(new Orders(LocalDateTime.now()));

        dao.saveAndFlush(goods);
    }

    @Override
    public List<Goods> getAllProduct() {

        return dao.findAll();
    }
}
