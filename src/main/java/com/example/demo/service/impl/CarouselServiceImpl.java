package com.example.demo.service.impl;

import com.example.demo.entity.Carousel;
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
    public List<Carousel> findAll() {
        return repository.findAll();
    }
}
