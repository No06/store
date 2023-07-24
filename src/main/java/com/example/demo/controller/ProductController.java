package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/getById")
    public ResponseEntity<?> getById(String id) {
        Product getProduct;
        try {
            getProduct = productService.findById(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(getProduct);
    }

    @PostMapping("/getByName")
    public ResponseEntity<?> getByName(String name) {
        return ResponseEntity.ok(productService.findByName(name));
    }

    @PostMapping("/getByCategory")
    public ResponseEntity<?> getByCategory(String category) {
        return ResponseEntity.ok(productService.findByCategory(category));
    }

    @PostMapping("/getByNameAndCategory")
    public ResponseEntity<?> getByNameAndCategory(String name, String category) {
        return ResponseEntity.ok(productService.findByNameAndCategory(name, category));
    }

    @PostMapping("/getByPriceRange")
    public ResponseEntity<?> getByPriceRange(double min, double max) {
        return ResponseEntity.ok(productService.findByPriceRange(min, max));
    }
}
