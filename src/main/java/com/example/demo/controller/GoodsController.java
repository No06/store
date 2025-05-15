package com.example.demo.controller;

import com.example.demo.entity.Goods;
import com.example.demo.entity.GoodsCategory;
import com.example.demo.entity.vo.GoodsCategoryWithCountVO;
import com.example.demo.entity.dto.goods.GoodsSaveDTO;
import com.example.demo.entity.dto.goods.GoodsShowItemDTO;
import com.example.demo.service.GoodsCategoryService;
import com.example.demo.service.GoodsPhotoService;
import com.example.demo.service.GoodsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name="商品接口")
@RestController
@RequestMapping("/goods")
public class GoodsController {
    private final GoodsService service;
    private final GoodsCategoryService categoryService;

    @Autowired
    public GoodsController(GoodsService service, GoodsCategoryService categoryService, GoodsPhotoService photoService) {
        this.service = service;
        this.categoryService = categoryService;
    }

    @Operation(summary="获取所有商品")
    @GetMapping("/getAll")
    public List<Goods> getAll() {
        return service.findAll();
    }

    @Operation(summary="根据ID获取商品")
    @GetMapping("/getById/{id}")
    public ResponseEntity<Goods> getById(@PathVariable Long id) {
        Optional<Goods> goods = service.findById(id);
        return goods.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary="根据ID获取商品简单信息")
    @GetMapping("/getItemById/{id}")
    public ResponseEntity<Goods> getItemById(@PathVariable Long id) {
        Optional<Goods> goods = service.findById(id);
        return goods.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary="根据商品名获取商品")
    @GetMapping("/getByName")
    public List<Goods> getByName(@RequestParam("name") String name) {
        return service.findByName(name);
    }

    @Operation(summary="根据类型获取商品")
    @GetMapping("/getByCategory")
    public List<Goods> getByCategory(@RequestParam("categoryName") String categoryName) {
        return service.findByCategoryName(categoryName);
    }

    @Operation(summary="根据商品名与类型获取商品")
    @GetMapping("/getByNameAndCategory")
    public List<Goods> getByNameAndCategory(@RequestParam("goodsName") String goodsName, @RequestParam("categoryName") String categoryName) {
        return service.findByNameAndCategoryName(goodsName, categoryName);
    }

    @Operation(summary="根据价格范围获取商品")
    @GetMapping("/getByPriceRange")
    public List<Goods> getByPriceRange(@RequestParam("min") Double min, @RequestParam("max") Double max) {
        return service.findByPriceRange(min, max);
    }

    @Operation(summary="分页获取商品")
    @PostMapping("/findAllByPage")
    public Page<Goods> findAllByPage(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long category_id,
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        return service.findByNameAndCategoryIdForPage(name, category_id, page, size);
    }

    @Operation(summary="模糊搜索商品")
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

    @Operation(summary="获取总商品数")
    @GetMapping("/getCount")
    public Long getCount() {
        return service.count();
    }

    @Operation(summary="统计分类中商品数")
    @GetMapping("/getCountByCategory")
    public List<GoodsCategoryWithCountVO> getCountByCategory() {
        return service.countByCategory();
    }

    @Operation(summary="保存商品", description = """
            需要管理员权限
            如果传入的 id 不存在，则创建新的商品
            如果传入的 id 存在，则更新对应的商品""")
    @PutMapping("/save")
    public ResponseEntity<String> save(@RequestBody GoodsSaveDTO dto, @RequestAttribute Boolean isAdmin) {
        if (isAdmin == null || !isAdmin) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("operation is not allow");
        }

        Goods goods = new Goods(dto);
        GoodsCategory category = goods.getCategory();

        if (category == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("category is null");
        }
        if (category.getId() != null) {
            service.save(goods);
            return ResponseEntity.ok().build();
        }
        if (category.getName() == null) {
            return ResponseEntity.badRequest().body("categoryName is null");
        }
        category = categoryService.findByName(goods.getCategory().getName());
        if (category == null) {
            category = categoryService.save(goods.getCategory());
        }
        goods.setCategory(category);

        service.save(goods);
        return ResponseEntity.ok().build();
    }

    @Operation(summary="删除商品", description = "需要登录和管理员权限")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, @RequestAttribute Boolean isAdmin) {
        if (isAdmin == null || !isAdmin) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("operation is not allow");
        }
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.ok("Delete success");
    }

    @Operation(summary="获取分类商品数量")
    @GetMapping("/get/count/byCategoryId/{id}")
    public ResponseEntity<Long> getCountByCategoryId(@PathVariable(name = "id") Long categoryId) {
        return ResponseEntity.ok(service.countByCategoryId(categoryId));
    }
}
