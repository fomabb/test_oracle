package com.kirilyuk.test_oracle.controller;

import com.kirilyuk.test_oracle.dto.OrdersReportDTO;
import com.kirilyuk.test_oracle.entity.Goods;
import com.kirilyuk.test_oracle.entity.Orders;
import com.kirilyuk.test_oracle.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Valid
public class ProductController {

    private final ProductService service;

    @PostMapping("/save")
    public List<Goods> createNewProduct(@RequestBody List<Goods> goods) {

        service.createNewProduct(goods);

        return goods;
    }

    @PostMapping("/order/save")
    public Orders saveOrders(@RequestBody Orders orders) {

        service.saveOrders(orders);

        return orders;
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

    @GetMapping("/orders/all/{id}")
    public List<Orders> getAllOrdersById(@PathVariable("id") Long id) {

        return service.getAllOrdersById(id);
    }

    @GetMapping("/weight")
    public double getWeight() {

        return service.weight();
    }

    @GetMapping("/date")
    public List<Orders> getDate(@RequestParam String text) {

        return service.getDate(text);
    }
}
