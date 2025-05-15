package com.example.demo.service.impl;

import com.example.demo.entity.Carousel;
import com.example.demo.repository.CarouselRepository;
import com.example.demo.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarouselServiceImpl implements CarouselService {
    private final CarouselRepository repository;

    @Autowired
    public CarouselServiceImpl(CarouselRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Carousel> findByPage(Integer page, Integer size) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        return repository.findAll(pageable);
    }

    @Override
    public List<Carousel> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Carousel> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Carousel> findByGoodsId(Long goodsId) {
        return repository.findByGoodsId(goodsId);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Carousel save(Carousel carousel) {
        return repository.save(carousel);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
