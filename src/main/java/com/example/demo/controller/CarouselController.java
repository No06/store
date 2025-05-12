package com.example.demo.controller;

import com.example.demo.entity.Carousel;
import com.example.demo.entity.dto.carousel.CarouselSaveDTO;
import com.example.demo.entity.vo.CarouselVO;
import com.example.demo.service.CarouselService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="轮播图接口", description="轮播图相关API")
@RestController
@RequestMapping("/carousel")
public class CarouselController {
    private final CarouselService carouselService;

    @Autowired
    public CarouselController(CarouselService carouselService) {
        this.carouselService = carouselService;
    }

    @Operation(summary="获取所有轮播图")
    @GetMapping("/list")
    public ResponseEntity<List<CarouselVO>> findAll() {
        return ResponseEntity.ok(carouselService.findAll().stream().map(CarouselVO::new).toList());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CarouselVO>> findPage(
            @RequestParam Integer page,
            @RequestParam Integer size
    ) {
        return ResponseEntity.ok().body(carouselService.findByPage(page, size).map(CarouselVO::new));
    }

    @Operation(summary = "保存或更新轮播图")
    @PutMapping("/save")
    public ResponseEntity<String> save(@RequestBody CarouselSaveDTO dto, @RequestAttribute Boolean isAdmin) {
        if (!isAdmin) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("operation not allowed");
        }
        if (dto.goodsId == null) {
            return ResponseEntity.badRequest().body("goodsId is null");
        }
        carouselService.save(new Carousel(dto));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteById(@RequestParam Long id, @RequestAttribute Boolean isAdmin) {
        if (isAdmin == null || !isAdmin) {
            return ResponseEntity.badRequest().body("operation is invalid");
        }
        if (!carouselService.existsById(id)) {
            return ResponseEntity.badRequest().body("carousel is not exist");
        }
        carouselService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
