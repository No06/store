package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductCategory;
import com.example.demo.service.ProductCategoryService;
import com.example.demo.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Product product;
        try {
            product = productService.findById(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
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
    public List<Product> getByPriceRange(@RequestParam("min") Double min, @RequestParam("max") Double max) {
        return productService.findByPriceRange(min, max);
    }

    @GetMapping("/getAllBySpec")
    public List<Product> getAllBySpec(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "false") boolean inStock,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Integer[] category_id
    ) {
        return productService.findAllBySpec(name, inStock, minPrice, maxPrice, category_id);
    }

    @GetMapping("/getCountProductsByCategory")
    public List<Object[]> getProductCountByCategory() {
        return productService.countProductsByCategory();
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            productService.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException("Entity with ID: [" + id + "] was not found");
        }
        return ResponseEntity.ok("Delete success");
    }
}
