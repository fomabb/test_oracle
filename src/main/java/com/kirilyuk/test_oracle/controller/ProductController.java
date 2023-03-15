package com.kirilyuk.test_oracle.controller;

import com.kirilyuk.test_oracle.dto.QuantityUpdateDTO;
import com.kirilyuk.test_oracle.entity.Goods;
import com.kirilyuk.test_oracle.entity.Orders;
import com.kirilyuk.test_oracle.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

@Transactional
public class ProductController {

    private final ProductService service;

//    *******************************************************Goods******************************************************

    @PostMapping("/goods/save")
    public List<Goods> createNewProduct(@RequestBody List<Goods> goods) {

        service.createNewProduct(goods);

        return goods;
    }

    @GetMapping("/goods/all")
    public List<Goods> getAllGoods() {

        return service.getAllGoods();
    }

    @GetMapping("/goods/{id}")
    public Optional<Goods> getGoodsById(@PathVariable("id") Long id) {

        return service.getGoodsById(id);
    }

    @DeleteMapping("/delete/goods/{id}")
    public void deleteGoods(@PathVariable("id") Long id) {

        service.deleteGoods(id);
    }

    @GetMapping("/search")
    public List<Goods> search(@RequestParam("text") String text) {

        return service.search(text);
    }

//    *******************************************************Orders*****************************************************

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

        Orders order = new Orders();

        getAllOrdersById(order.getId());

        return service.getOrdersTable();
    }

    @DeleteMapping("/delete/order/{id}")
    public String deleteOrder(@PathVariable("id") Long id) {

        service.deleteOrder(id);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formatDateTime = now.format(formatter);

        return "Order with id:" + id + " was deleted " + formatDateTime;
    }

    @GetMapping("/orders/all/{id}")
    public List<Goods> getAllOrdersById(@PathVariable("id") Long id) {

        return service.getAllOrdersById(id);
    }

    @PutMapping("/update/quantity/{id}")
    public QuantityUpdateDTO updateQuantity(@PathVariable("id") Long id,
                                            @RequestBody QuantityUpdateDTO quantity) {

        service.updateQuantity(id, quantity);

        return quantity;
    }

    @GetMapping("/registry/{orderId}")
    public List<Double> registry(@PathVariable("orderId") Long orderId) {

        return service.registry(orderId);
    }
}