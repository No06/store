package com.example.demo.controller;

import com.example.demo.entity.GoodsCategory;
import com.example.demo.service.GoodsCategoryService;
import com.example.demo.service.GoodsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="商品分类接口", description="分类相关API")
@RestController
@RequestMapping("/goods/category")
public class GoodsCategoryController {
    private final GoodsCategoryService categoryService;
    private final GoodsService goodsService;

    @Autowired
    public GoodsCategoryController(GoodsCategoryService categoryService, GoodsService goodsService) {
        this.categoryService = categoryService;
        this.goodsService = goodsService;
    }

    @Operation(summary="查询所有分类")
    @GetMapping("/getAll")
    public ResponseEntity<List<GoodsCategory>> getAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @Operation(summary="保存分类信息")
    @PutMapping("/save")
    public ResponseEntity<String> save(@RequestBody GoodsCategory category) {
        if (category.getId() == null && categoryService.existsByName(category.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("category name: " + category.getName() + " is already exist.");
        }
        categoryService.save(category);
        return ResponseEntity.ok().build();
    }

    @Operation(summary="分页查询分类")
    @GetMapping("/getPage")
    public ResponseEntity<Page<GoodsCategory>> getPage(
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        return ResponseEntity.ok(categoryService.findPage(page, size));
    }

    @Operation(summary="删除分类")
    @DeleteMapping("/remove/byId/{id}")
    public ResponseEntity<String> removeById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("id is null");
        }
        Long count = goodsService.countByCategoryId(id);
        if (count != 0) {
            return ResponseEntity.badRequest().body("number of category is not zero");
        }
        categoryService.removeById(id);
        return ResponseEntity.ok().build();
    }
}
