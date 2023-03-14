package com.kirilyuk.test_oracle.controller;

import com.kirilyuk.test_oracle.entity.Goods;
import com.kirilyuk.test_oracle.entity.Orders;
import com.kirilyuk.test_oracle.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @GetMapping("/goods/order/all")
    public List<Goods> getAllGootsOrder() {

        return service.getAllGootsOrder();
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

    /*
    ToDo
     */
    @PutMapping("/order/update/goods")
    public Orders orderUpdateGoods(@RequestBody Orders order, Long id) {

        service.orderUpdateGoods(order, id);

        return order;
    }

    @GetMapping("/order/{id}")
    public Optional<Orders> getOrderById(@PathVariable("id") Long id) {

        return service.getOrderById(id);
    }

    @GetMapping("/orders")
    public List<Orders> getOrdersTable() {

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

    @GetMapping("orders/info/{id}")
    public List<Orders> getOrdersInfo(@PathVariable("id") Long id) {

        return service.getOrdersInfo(id);
    }
}