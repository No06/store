package com.example.demo.service;

import com.example.demo.entity.Carousel;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CarouselService {
    Page<Carousel> findByPage(Integer page, Integer size);
    List<Carousel> findAll();
    Optional<Carousel> findById(Long id);
    Optional<Carousel> findByGoodsId(Long goodsId);
    boolean existsById(Long id);
    Carousel save(Carousel carousel);
    void deleteById(Long id);
}
