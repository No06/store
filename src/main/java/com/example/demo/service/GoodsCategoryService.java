package com.example.demo.service;

import com.example.demo.entity.GoodsCategory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

public interface GoodsCategoryService {
    List<GoodsCategory> findAll();

    Optional<GoodsCategory> findById(Long id);

    GoodsCategory findByName(String name);

    void removeById(Long id);

    GoodsCategory save(GoodsCategory category);

    Page<GoodsCategory> findPage(Integer page, Integer size);

    boolean existsByName(String name);
}
