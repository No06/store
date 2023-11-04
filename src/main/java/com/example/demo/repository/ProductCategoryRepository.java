package com.example.demo.repository;

import com.example.demo.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    @Query("select new ProductCategory(c.id, c.name, c.description) from ProductCategory c")
    List<ProductCategory> findAllVO();
}
