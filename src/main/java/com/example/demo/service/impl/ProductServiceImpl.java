package com.example.demo.service.impl;

import com.example.demo.dao.ProductRepository;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    @Override
    public Product findById(String id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Product> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Product> findByCategory(String category) {
        return repository.findByCategory(category);
    }

    @Override
    public List<Product> findByNameAndCategory(String name, String category) {
        return repository.findByNameAndCategory(name, category);
    }

    @Override
    public List<Product> findByPriceRange(double min, double max) {
        return repository.findByPriceRange(min, max);
    }
}
