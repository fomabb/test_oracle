package com.kirilyuk.test_oracle.controller;

import com.kirilyuk.test_oracle.entity.Goods;
import com.kirilyuk.test_oracle.entity.Orders;
import com.kirilyuk.test_oracle.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

//    ***************Goods***************

    @PostMapping("/goods/save")
    public List<Goods> createNewProduct(@RequestBody List<Goods> goods) {

        service.createNewProduct(goods);

        return goods;
    }

    @PutMapping("/goods/update")
    public Goods updateNewProduct(@RequestBody Goods goods) {

        service.update(goods);

        return goods;
    }

    @GetMapping("/goods/all")
    public List<Goods> getAllProduct() {

        return service.getAllProduct();
    }

    @GetMapping("/goods/{id}")
    public Optional<Goods> getGoodsById(@PathVariable("id") Long id) {

        return service.getGoodsById(id);
    }

    @DeleteMapping("/delete/goods/{id}")
    public void deleteGoods(@PathVariable("id") Long id) {

        service.deleteGoods(id);
    }

    //    ***************Orders***************

    @PostMapping("/save/order")
    public Orders saveOrder(@RequestBody Orders orders) {

        service.saveOrder(orders);

        return orders;
    }

    @PutMapping("/add/order/{orderId}/goods/{goodsId}")
    public String addGoodsInOrder(@PathVariable("orderId") Long orderId,
                                  @PathVariable("goodsId") Long goodsId) {

        service.addGoodsInOrder(orderId, goodsId);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formatDateTime = now.format(formatter);

        return "Product with id:" + goodsId + " added to cart " + formatDateTime;
    }

    @GetMapping("/order/{id}")
    public Optional<Orders> getOrderById(@PathVariable("id") Long id) {

        return service.getOrderById(id);
    }

    @GetMapping("/orders")
    public List<Orders> getOrdersTable() {

        return service.getOrdersTable();
    }
}
