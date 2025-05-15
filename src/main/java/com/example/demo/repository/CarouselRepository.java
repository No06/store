package com.example.demo.repository;

import com.example.demo.entity.Carousel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarouselRepository extends JpaRepository<Carousel, Long> {
    Optional<Carousel> findByGoodsId(Long goodsId);
}
