package com.example.demo.controller;

import com.example.demo.entity.Goods;
import com.example.demo.entity.dto.goods.GoodsCountByCategoryDTO;
import com.example.demo.entity.dto.goods.GoodsSaveDTO;
import com.example.demo.entity.dto.goods.GoodsShowItemDTO;
import com.example.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    private final GoodsService service;

    @Autowired
    public GoodsController(GoodsService service) {
        this.service = service;
    }

    // 获取所有商品
    @GetMapping("/getAll")
    public List<Goods> getAll() {
        return service.findAll();
    }

    // 获取所有商品简单信息
    @GetMapping("/getAllItems")
    public List<Goods> getAllItems() {
        return service.findAll();
    }

    // 根据ID获取商品
    @GetMapping("/getById/{id}")
    public ResponseEntity<Goods> getById(@PathVariable Long id) {
        Optional<Goods> goods = service.findById(id);
        return goods.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 根据ID获取商品简单信息
    @GetMapping("/getItemById/{id}")
    public ResponseEntity<Goods> getItemById(@PathVariable Long id) {
        Optional<Goods> goods = service.findById(id);
        return goods.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 根据商品名获取所有商品
    @GetMapping("/getByName")
    public List<Goods> getByName(@RequestParam("name") String name) {
        return service.findByName(name);
    }

    // 根据商品名获取所有商品简单信息
    @GetMapping("/getItemsByName")
    public List<GoodsShowItemDTO> getItemsByName(@RequestParam("name") String name) {
        return service.findAllItemBySpec(name, null, null, null, null);
    }

    // 根据类型获取所有商品
    @GetMapping("/getByCategory")
    public List<Goods> getByCategory(@RequestParam("categoryName") String categoryName) {
        return service.findByCategoryName(categoryName);
    }

    // 根据商品类型获取商品简单信息
    @GetMapping("/getItemsByCategory")
    public List<Goods> getItemsByCategory(@RequestParam("categoryName") String categoryName) {
        return service.findByCategoryName(categoryName);
    }

    // 根据商品名与类型获取商品
    @GetMapping("/getByNameAndCategory")
    public List<Goods> getByNameAndCategory(@RequestParam("goodsName") String goodsName, @RequestParam("categoryName") String categoryName) {
        return service.findByNameAndCategoryName(goodsName, categoryName);
    }

    // 根据价格锁定范围查找
    @GetMapping("/getByPriceRange")
    public List<Goods> getByPriceRange(@RequestParam("min") Double min, @RequestParam("max") Double max) {
        return service.findByPriceRange(min, max);
    }

    // 分页查找
    @RequestMapping("/findAllByPage")
    public Page<Goods> findAllByPage(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long category_id,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        return service.findByNameAndCategoryIdForPage(name, category_id, page, size);
    }

    // 模糊查找
    @GetMapping("/getAllItemBySpec")
    public List<GoodsShowItemDTO> getAllItemBySpec(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "false") boolean inStock,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Long[] category_id
    ) {
        return service.findAllItemBySpec(name, inStock, minPrice, maxPrice, category_id);
    }

    // 获取总商品数
    @GetMapping("/getCount")
    public Long getCount() {
        return service.count();
    }

    // 获取商品类中商品总数
    @GetMapping("/getCountByCategory")
    public List<GoodsCountByCategoryDTO> getCountByCategory() {
        return service.countByCategory();
    }

    // 保存商品
    @PutMapping("/save")
    public ResponseEntity<String> updateGoods(@RequestBody GoodsSaveDTO goods) {
        service.save(goods);
        return ResponseEntity.ok().build();
    }

    // 删除商品
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.ok("Delete success");
    }

    @GetMapping("/get/count/byCategoryId/{id}")
     public ResponseEntity<Long> getCountByCategoryId(@PathVariable(name = "id") Long categoryId) {
         return ResponseEntity.ok(service.countByCategoryId(categoryId));
     }
}
