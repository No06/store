package com.example.demo.service;

import com.example.demo.entity.dto.ProductDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    ProductDTO findById(Integer id);
    List<ProductDTO> findAll();
    List<ProductDTO> findByName(String name);
    List<ProductDTO> findByCategoryName(String categoryName);
    List<ProductDTO> findByNameAndCategoryName(String productName, String categoryName);
    List<ProductDTO> findByPriceRange(Double min, Double max);
    Page<ProductDTO> findByNameAndCategoryIdForPage(String name, Integer category_id, Integer page, Integer size);
    List<ProductDTO> findAllItemBySpec(String name, boolean inStock, Double minPrice, Double maxPrice, Integer[] category_id);
    void save(ProductDTO product);
    void deleteById(Integer id);
    List<Object[]> countByCategory();
    Long count();
}
