package com.example.demo.service;

import com.example.demo.entity.Product;

import java.util.List;

public interface ProductService {
    Product findById(Integer id);
    List<Product> findAll();
    List<Product> findByName(String name);
    List<Product> findByCategoryName(String categoryName);
    List<Product> findByNameAndCategoryName(String productName, String categoryName);
    List<Product> findByPriceRange(Double min, Double max);
    List<Product> findAllBySpec(String name, boolean inStock, Double minPrice, Double maxPrice, Integer[] category_id);
    void save(Product product);
    void deleteById(Integer id);
    List<Object[]> countProductsByCategory();
}
