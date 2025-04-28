package com.example.demo.service;

import com.example.demo.entity.Goods;
import com.example.demo.entity.dto.goods.GoodsCountByCategoryDTO;
import com.example.demo.entity.dto.goods.GoodsSaveDTO;

import org.springframework.data.domain.Page;

import java.util.List;

public interface GoodsService {
    Goods findById(Long id);
    List<Goods> findAll();
    List<Goods> findByName(String name);
    List<Goods> findByCategoryName(String categoryName);
    List<Goods> findByNameAndCategoryName(String name, String categoryName);
    List<Goods> findByPriceRange(Double min, Double max);
    Page<Goods> findByNameAndCategoryIdForPage(String name, Long category_id, Integer page, Integer size);
    List<Goods> findAllItemBySpec(String name, boolean inStock, Double minPrice, Double maxPrice, Long[] category_id);
    void save(GoodsSaveDTO goods);
    void deleteById(Long id);
    List<GoodsCountByCategoryDTO> countByCategory();
    Long count();
    Long countByCategoryId(Long categoryId);
}
