package com.example.demo.service.impl;

import com.example.demo.entity.ProductCategory;
import com.example.demo.entity.dto.ProductCategoryDTO;
import com.example.demo.repository.ProductCategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductCategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository repository;

    private final ProductRepository productRepository;

    @Autowired
    public ProductCategoryServiceImpl(ProductCategoryRepository repository, ProductRepository productRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductCategoryDTO> findAll() {
        return repository.findAll().stream().map(ProductCategoryDTO::fromPO).toList();
    }

    @Override
    public ProductCategoryDTO findById(Long id) {
        return ProductCategoryDTO.fromPO(repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id))));
    }

    @Override
    public void removeById(Long id) {
        Long count = productRepository.countByCategoryId(id);
        if (count != 0) {
            throw new IllegalStateException("请确保要删除的商品类之下没有商品");
        }
        repository.removeById(id);
    }

    @Override
    public void save(ProductCategoryDTO categoryDTO) {
        repository.save(ProductCategory.fromDTO(categoryDTO));
    }

    @Override
    public Page<ProductCategoryDTO> findPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return repository.findAll(pageable).map(ProductCategoryDTO::fromPO);
    }
}
