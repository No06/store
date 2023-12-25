package com.example.demo.service;

import com.example.demo.entity.dto.ProductCategoryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategoryDTO> findAll();
    ProductCategoryDTO findById(Long id);
    void removeById(Long id);
    void save(ProductCategoryDTO categoryDTO);
    Page<ProductCategoryDTO> findPage(Integer page, Integer size);
}
