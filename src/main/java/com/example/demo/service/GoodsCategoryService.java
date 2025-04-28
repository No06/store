package com.example.demo.service;

import com.example.demo.entity.GoodsCategory;

import java.util.List;

import org.springframework.data.domain.Page;

public interface GoodsCategoryService {
    List<GoodsCategory> findAll();

    GoodsCategory findById(Long id);

    void removeById(Long id);

    void save(GoodsCategory category);

    Page<GoodsCategory> findPage(Integer page, Integer size);
}
