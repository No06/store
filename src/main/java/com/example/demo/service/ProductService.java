package com.example.demo.service;

import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product findById(Long id);
    List<Product> findAll();
    List<Product> findByName(String name);
    List<Product> findByCategoryName(String categoryName);
    List<Product> findByNameAndCategoryName(String productName, String categoryName);
    List<Product> findByPriceRange(Double min, Double max);
    Page<Product> findByNameAndCategoryIdForPage(String name, Long category_id, Integer page, Integer size);
    List<Product> findAllItemBySpec(String name, boolean inStock, Double minPrice, Double maxPrice, Long[] category_id);
    void save(Product product);
    void deleteById(Long id);
    List<Object[]> countByCategory();
    Long count();
    Long countByCategoryId(Long categoryId);
}
