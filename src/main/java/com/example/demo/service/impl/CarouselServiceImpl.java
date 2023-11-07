package com.example.demo.service.impl;

import com.example.demo.entity.dto.CarouselDTO;
import com.example.demo.repository.CarouselRepository;
import com.example.demo.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {
    private final CarouselRepository repository;

    @Autowired
    public CarouselServiceImpl(CarouselRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CarouselDTO> findAll() {
        return repository.findAll().stream().map(CarouselDTO::fromCarousel).toList();
    }
}
