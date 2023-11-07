package com.example.demo.controller;

import com.example.demo.entity.vo.CarouselVO;
import com.example.demo.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carousel")
public class CarouselController {
    private final CarouselService carouselService;

    @Autowired
    public CarouselController(CarouselService carouselService) {
        this.carouselService = carouselService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<CarouselVO>> findAll() {
        return ResponseEntity.ok(carouselService.findAll().stream().map(CarouselVO::fromCarouselDTO).toList());
    }
}
