package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<Product> getAll() {
        return productService.findAll();
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam("id") int id) {
        Product getProduct;
        try {
            getProduct = productService.findById(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(getProduct);
    }

    @GetMapping("/getByName")
    public List<Product> getByName(@RequestParam("name") String name) {
        return productService.findByName(name);
    }

    @GetMapping("/getByCategory")
    public List<Product> getByCategory(@RequestParam("categoryName") String categoryName) {
        return productService.findByCategoryName(categoryName);
    }

    @GetMapping("/getByNameAndCategory")
    public List<Product> getByNameAndCategory(@RequestParam("productName") String productName, @RequestParam("categoryName") String categoryName) {
        return productService.findByNameAndCategoryName(productName, categoryName);
    }

    @GetMapping("/getByPriceRange")
    public ResponseEntity<?> getByPriceRange(@RequestParam("min") double min, @RequestParam("max") double max) {
        return ResponseEntity.ok(productService.findByPriceRange(min, max));
    }
}
