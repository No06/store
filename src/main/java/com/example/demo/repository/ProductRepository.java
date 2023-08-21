package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductCategory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    List<Product> findAll(Specification<Product> spec);

    List<Product> findByName(String name);

    @Query("select p from Product p where p.category.name = ?1")
    List<Product> findByCategoryName(String categoryName);

    @Query("select p from Product p where p.name = ?1 and p.category.name = ?2")
    List<Product> findByNameAndCategoryName(String productName, String categoryName);

    @Query (value = "select p from Product p where p.price > ?1 and p.price < ?2")
    List<Product> findByPriceRange(Double min, Double max);

    @Query("SELECT p.category, COUNT(p) FROM Product p GROUP BY p.category")
    List<Object[]> countProductsByCategory();
}
