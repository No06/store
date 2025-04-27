package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductCategory;
import com.example.demo.service.ProductCategoryService;
import com.example.demo.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
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

    // 获取所有商品
    @GetMapping("/getAll")
    public List<Product> getAll() {
        return productService.findAll();
    }

    // 获取所有商品简单信息
    @GetMapping("/getAllItems")
    public List<Product> getAllItems() {
        return productService.findAll();
    }

    // 获取所有商品类型
    @GetMapping("/getAllCategory")
    public List<ProductCategory> getAllCategory() {
        return categoryService.findAll();
    }

    // 根据ID获取商品
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(productService.findById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 根据ID获取商品简单信息
    @GetMapping("/getItemById/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(productService.findById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 根据商品名获取所有商品
    @GetMapping("/getByName")
    public List<Product> getByName(@RequestParam("name") String name) {
        return productService.findByName(name);
    }

    // 根据商品名获取所有商品简单信息
    @GetMapping("/getItemsByName")
    public List<Product> getItemsByName(@RequestParam("name") String name) {
        return productService.findByName(name);
    }

    // 根据类型获取所有商品
    @GetMapping("/getByCategory")
    public List<Product> getByCategory(@RequestParam("categoryName") String categoryName) {
        return productService.findByCategoryName(categoryName);
    }

    // 根据商品类型获取商品简单信息
    @GetMapping("/getItemsByCategory")
    public List<Product> getItemsByCategory(@RequestParam("categoryName") String categoryName) {
        return productService.findByCategoryName(categoryName);
    }

    // 根据商品名与类型获取商品
    @GetMapping("/getByNameAndCategory")
    public List<Product> getByNameAndCategory(@RequestParam("productName") String productName, @RequestParam("categoryName") String categoryName) {
        return productService.findByNameAndCategoryName(productName, categoryName);
    }

    // 根据价格锁定范围查找
    @GetMapping("/getByPriceRange")
    public List<Product> getByPriceRange(@RequestParam("min") Double min, @RequestParam("max") Double max) {
        return productService.findByPriceRange(min, max);
    }

    // 分页查找
    @RequestMapping("/findAllByPage")
    public Page<Product> findAllByPage(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long category_id,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        return productService.findByNameAndCategoryIdForPage(name, category_id, page, size);
    }

    // 模糊查找
    @GetMapping("/getAllItemBySpec")
    public List<Product> getAllItemBySpec(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "false") boolean inStock,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Long[] category_id
    ) {
        return productService.findAllItemBySpec(name, inStock, minPrice, maxPrice, category_id);
    }

    // 获取总商品数
    @GetMapping("/getCount")
    public Long getCount() {
        return productService.count();
    }

    // 获取商品类中商品总数
    @GetMapping("/getCountByCategory")
    public List<Object[]> getCountByCategory() {
        return productService.countByCategory();
    }

    // 保存商品
    @PutMapping("/save")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        try {
            productService.save(product);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    // 删除商品
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            productService.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException("Entity with ID: [" + id + "] was not found");
        }
        return ResponseEntity.ok("Delete success");
    }
}
