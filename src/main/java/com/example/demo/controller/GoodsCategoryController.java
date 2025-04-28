package com.example.demo.controller;

import com.example.demo.entity.GoodsCategory;
import com.example.demo.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods/category")
public class GoodsCategoryController {
    private final GoodsCategoryService categoryService;

    @Autowired
    public GoodsCategoryController(GoodsCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<GoodsCategory>> getAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PutMapping("/save")
    public ResponseEntity<Void> save(@RequestBody GoodsCategory category) {
        categoryService.save(category);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getPage")
    public ResponseEntity<Page<GoodsCategory>> getPage(
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        return ResponseEntity.ok(categoryService.findPage(page, size));
    }

    @DeleteMapping("/remove/byId/{id}")
    public ResponseEntity<Void> removeById(@PathVariable Long id) {
        categoryService.removeById(id);
        return ResponseEntity.ok().build();
    }
}
