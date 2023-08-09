package com.example.demo.service;

import com.example.demo.entity.Product;

import java.util.List;

public interface ProductService {
    Product findById(int id);
    List<Product> findAll();
    List<Product> findByName(String name);
    List<Product> findByCategoryName(String categoryName);
    List<Product> findByNameAndCategoryName(String productName, String categoryName);
    List<Product> findByPriceRange(double min, double max);
    Product save(Product product);
}
