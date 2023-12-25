package com.example.demo.controller;

import com.example.demo.entity.dto.ProductDTO;
import com.example.demo.entity.vo.ProductSimpleVO;
import com.example.demo.entity.vo.ProductVO;
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

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 获取所有商品
    @GetMapping("/getAll")
    public List<ProductVO> getAll() {
        return productService.findAll().stream().map(ProductVO::fromProductDTO).toList();
    }

    // 获取所有商品简单信息
    @GetMapping("/getAllItems")
    public List<ProductSimpleVO> getAllItems() {
        return productService.findAll().stream().map(ProductSimpleVO::fromProductDTO).toList();
    }

    // 根据ID获取商品
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ProductVO.fromProductDTO(productService.findById(id)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 根据ID获取商品简单信息
    @GetMapping("/getItemById/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ProductSimpleVO.fromProductDTO(productService.findById(id)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 根据商品名获取所有商品
    @GetMapping("/getByName")
    public List<ProductVO> getByName(@RequestParam("name") String name) {
        return productService.findByName(name).stream().map(ProductVO::fromProductDTO).toList();
    }

    // 根据商品名获取所有商品简单信息
    @GetMapping("/getItemsByName")
    public List<ProductSimpleVO> getItemsByName(@RequestParam("name") String name) {
        return productService.findByName(name).stream().map(ProductSimpleVO::fromProductDTO).toList();
    }

    // 根据类型获取所有商品
    @GetMapping("/getByCategory")
    public List<ProductVO> getByCategory(@RequestParam("categoryName") String categoryName) {
        return productService.findByCategoryName(categoryName).stream().map(ProductVO::fromProductDTO).toList();
    }

    // 根据商品类型获取商品简单信息
    @GetMapping("/getItemsByCategory")
    public List<ProductSimpleVO> getItemsByCategory(@RequestParam("categoryName") String categoryName) {
        return productService.findByCategoryName(categoryName).stream().map(ProductSimpleVO::fromProductDTO).toList();
    }

    // 根据商品名与类型获取商品
    @GetMapping("/getByNameAndCategory")
    public List<ProductVO> getByNameAndCategory(@RequestParam("productName") String productName, @RequestParam("categoryName") String categoryName) {
        return productService.findByNameAndCategoryName(productName, categoryName).stream().map(ProductVO::fromProductDTO).toList();
    }

    // 根据价格锁定范围查找
    @GetMapping("/getByPriceRange")
    public List<ProductSimpleVO> getByPriceRange(@RequestParam("min") Double min, @RequestParam("max") Double max) {
        return productService.findByPriceRange(min, max).stream().map(ProductSimpleVO::fromProductDTO).toList();
    }

    // 分页查找
    @RequestMapping("/findAllByPage")
    public Page<ProductVO> findAllByPage(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long category_id,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        return productService.findByNameAndCategoryIdForPage(name, category_id, page, size).map(ProductVO::fromProductDTO);
    }

    // 模糊查找
    @GetMapping("/getAllItemBySpec")
    public List<ProductSimpleVO> getAllItemBySpec(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "false") boolean inStock,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Long[] category_id
    ) {
        return productService.findAllItemBySpec(name, inStock, minPrice, maxPrice, category_id).stream().map(ProductSimpleVO::fromProductDTO).toList();
    }

    // 获取总商品数
    @GetMapping("/getCount")
    public Long getCount() {
        return productService.count();
    }

    // 获取商品类中商品总数
    // FIXME: 替换 Object[]
    @GetMapping("/getCountByCategory")
    public List<Object[]> getCountByCategory() {
        return productService.countByCategory();
    }

    // 保存商品
    @PutMapping("/save")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO product) {
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

    @GetMapping("/get/count/byCategoryId/{id}")
    public ResponseEntity<Long> getCountByCategoryId(@PathVariable(name = "id") Long categoryId) {
        return ResponseEntity.ok(productService.countByCategoryId(categoryId));
    }
}
