package com.example.demo.controller;

import com.example.demo.entity.dto.ProductDTO;
import com.example.demo.entity.vo.ProductCategoryVO;
import com.example.demo.entity.vo.ProductItemVO;
import com.example.demo.entity.vo.ProductVO;
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

    @GetMapping("/getAll")
    public List<ProductVO> getAll() {
        return productService.findAll().stream().map(ProductVO::fromProductDTO).toList();
    }

    @GetMapping("/getAllItems")
    public List<ProductItemVO> getAllItems() {
        return productService.findAll().stream().map(ProductItemVO::fromProductDTO).toList();
    }

    @GetMapping("/getAllCategory")
    public List<ProductCategoryVO> getAllCategory() {
        return categoryService.findAllVO().stream().map(ProductCategoryVO::fromDTO).toList();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(ProductVO.fromProductDTO(productService.findById(id)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getItemById/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(ProductItemVO.fromProductDTO(productService.findById(id)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getByName")
    public List<ProductVO> getByName(@RequestParam("name") String name) {
        return productService.findByName(name).stream().map(ProductVO::fromProductDTO).toList();
    }

    @GetMapping("/getItemsByName")
    public List<ProductItemVO> getItemsByName(@RequestParam("name") String name) {
        return productService.findByName(name).stream().map(ProductItemVO::fromProductDTO).toList();
    }

    @GetMapping("/getByCategory")
    public List<ProductVO> getByCategory(@RequestParam("categoryName") String categoryName) {
        return productService.findByCategoryName(categoryName).stream().map(ProductVO::fromProductDTO).toList();
    }

    @GetMapping("/getItemsByCategory")
    public List<ProductItemVO> getItemsByCategory(@RequestParam("categoryName") String categoryName) {
        return productService.findByCategoryName(categoryName).stream().map(ProductItemVO::fromProductDTO).toList();
    }

    @GetMapping("/getByNameAndCategory")
    public List<ProductVO> getByNameAndCategory(@RequestParam("productName") String productName, @RequestParam("categoryName") String categoryName) {
        return productService.findByNameAndCategoryName(productName, categoryName).stream().map(ProductVO::fromProductDTO).toList();
    }

    @GetMapping("/getByPriceRange")
    public List<ProductItemVO> getByPriceRange(@RequestParam("min") Double min, @RequestParam("max") Double max) {
        return productService.findByPriceRange(min, max).stream().map(ProductItemVO::fromProductDTO).toList();
    }

    @RequestMapping("/findAllByPage")
    public Page<ProductVO> findAllByPage(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer category_id,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        return productService.findByNameAndCategoryIdForPage(name, category_id, page, size).map(ProductVO::fromProductDTO);
    }

    @GetMapping("/getAllItemBySpec")
    public List<ProductItemVO> getAllItemBySpec(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "false") boolean inStock,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Integer[] category_id
    ) {
        return productService.findAllItemBySpec(name, inStock, minPrice, maxPrice, category_id).stream().map(ProductItemVO::fromProductDTO).toList();
    }

    @GetMapping("/getCount")
    public Long getCount() {
        return productService.count();
    }

    @GetMapping("/getCountByCategory")
    public List<Object[]> getCountByCategory() {
        return productService.countByCategory();
    }

    @PutMapping("/save")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO product) {
        try {
            productService.save(product);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
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
