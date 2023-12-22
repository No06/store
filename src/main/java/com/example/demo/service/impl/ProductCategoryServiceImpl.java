package com.example.demo.service.impl;

import com.example.demo.entity.dto.ProductCategoryDTO;
import com.example.demo.repository.ProductCategoryRepository;
import com.example.demo.service.ProductCategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository repository;

    @Autowired
    public ProductCategoryServiceImpl(ProductCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductCategoryDTO> findAll() {
        return repository.findAll().stream().map(ProductCategoryDTO::fromPO).toList();
    }

    @Override
    public ProductCategoryDTO findById(Long id) {
        return ProductCategoryDTO.fromPO(repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id))));
    }
}
