package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductCategory;
import com.example.demo.service.ProductCategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.utils.TokenUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final ProductCategoryService categoryService;
    @Autowired
    public ProductController(ProductService productService, ProductCategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/getAll")
    public List<Product> getAll() {
        return productService.findAll();
    }

    @GetMapping("/getAllCategory")
    public List<ProductCategory> getAllCategory() {
        return categoryService.findAll();
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

    @PutMapping("/save")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        try {
            productService.save(product);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
        return ResponseEntity.ok("");
    }
}
