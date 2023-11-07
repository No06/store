package com.example.demo.service;

import com.example.demo.entity.dto.CarouselDTO;

import java.util.List;

public interface CarouselService {
    List<CarouselDTO> findAll();
}
