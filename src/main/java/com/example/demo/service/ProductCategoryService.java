package com.example.demo.service;

import com.example.demo.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> findAll();
    ProductCategory findById(Long id);
}
