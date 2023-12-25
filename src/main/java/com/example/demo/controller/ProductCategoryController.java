package com.example.demo.controller;

import com.example.demo.entity.dto.ProductCategoryDTO;
import com.example.demo.entity.vo.ProductCategoryVO;
import com.example.demo.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/category")
public class ProductCategoryController {
    private final ProductCategoryService categoryService;

    @Autowired
    public ProductCategoryController(ProductCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductCategoryVO>> getAll() {
        return ResponseEntity.ok(categoryService.findAll().stream().map(ProductCategoryVO::fromDTO).toList());
    }

    @PutMapping("/save")
    public ResponseEntity<Void> save(@RequestBody ProductCategoryDTO categoryDTO) {
        categoryService.save(categoryDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getPage")
    public ResponseEntity<Page<ProductCategoryVO>> getPage(
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        return ResponseEntity.ok(categoryService.findPage(page, size).map(ProductCategoryVO::fromDTO));
    }

    @DeleteMapping("/remove/byId/{id}")
    public ResponseEntity<Void> removeById(@PathVariable Long id) {
        categoryService.removeById(id);
        return ResponseEntity.ok().build();
    }
}
