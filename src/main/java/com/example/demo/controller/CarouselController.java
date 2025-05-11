package com.example.demo.controller;

import com.example.demo.entity.Carousel;
import com.example.demo.service.CarouselService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<Carousel>> findAll() {
        return ResponseEntity.ok(carouselService.findAll());
    }
}
