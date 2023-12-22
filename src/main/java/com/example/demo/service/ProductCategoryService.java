package com.example.demo.service;

import com.example.demo.entity.dto.ProductCategoryDTO;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategoryDTO> findAll();
    ProductCategoryDTO findById(Long id);
}
