package com.example.demo.service;

import com.example.demo.entity.Product;

import java.util.List;

public interface ProductService {
    Product findById(String id);
    List<Product> findAll();
    List<Product> findByName(String name);
    List<Product> findByCategory(String category);
    List<Product> findByNameAndCategory(String name, String category);
    List<Product> findByPriceRange(double min, double max);
}
