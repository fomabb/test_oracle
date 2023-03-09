package com.kirilyuk.test_oracle.controller;

import com.kirilyuk.test_oracle.entity.Goods;
import com.kirilyuk.test_oracle.entity.Orders;
import com.kirilyuk.test_oracle.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping("/save")
    public List<Goods> createNewProduct(@RequestBody List<Goods> goods) {

        service.createNewProduct(goods);

        return goods;
    }


    @PutMapping("/update")
    public Goods updateNewProduct(@RequestBody Goods goods) {

        service.update(goods);

        return goods;
    }

    @GetMapping("/all")
    public List<Goods> getAllProduct() {

        return service.getAllProduct();
    }

    @GetMapping("/orders")
    public List<Orders> getOrdersTable() {

        return service.getOrdersTable();
    }

    @GetMapping("/{id}")
    public Optional<Goods> getGoodsById(@PathVariable("id") Long id) {

        return service.getGoodsById(id);
    }

    @GetMapping("/orders/{id}")
    public Optional<Orders> getByIdOrders(@PathVariable("id") Long id) {

        return service.getByIdOrders(id);
    }
}
