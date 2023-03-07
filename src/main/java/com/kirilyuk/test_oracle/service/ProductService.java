package com.kirilyuk.test_oracle.service;

import com.kirilyuk.test_oracle.entity.Goods;

import java.util.List;

public interface ProductService {

    void createNewProduct(Goods goods);

    List<Goods> getAllProduct();
}
