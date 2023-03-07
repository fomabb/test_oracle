package com.kirilyuk.test_oracle.controller;

import com.kirilyuk.test_oracle.entity.Goods;
import com.kirilyuk.test_oracle.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping("/save")
    public Goods createNewProduct(@RequestBody Goods goods) {

        service.createNewProduct(goods);

        return goods;
    }

    @PutMapping("/update")
    public Goods updateNewProduct(@RequestBody Goods goods) {

        service.createNewProduct(goods);

        return goods;
    }

    @GetMapping("/all")
    public List<Goods> getAllProduct() {

        return service.getAllProduct();
    }
}
