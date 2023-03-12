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

//    ***************Goods***************

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

    @GetMapping("/{id}")
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

    @PutMapping("/order/update/{orderId}/{goodsId}")
    public String updateOrders(@PathVariable("orderId") Long orderId,
                               @PathVariable("goodsId") Long goodsId) {

        service.updateOrder(orderId, goodsId);

        return "Request is exist";
    }

    @GetMapping("/orders/{id}")
    public Optional<Orders> getOrderById(@PathVariable("id") Long id) {

        return service.getOrderById(id);
    }

    @GetMapping("/orders/all/{id}")
    public List<Orders> getAllOrdersById(@PathVariable("id") Long id) {

        return service.getAllOrdersById(id);
    }

    @GetMapping("/orders")
    public List<Orders> getOrdersTable() {

        return service.getOrdersTable();
    }
}
