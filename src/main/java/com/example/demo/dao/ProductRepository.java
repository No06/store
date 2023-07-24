package com.example.demo.dao;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByName(String name);
    List<Product> findByCategory(String category);
    List<Product> findByNameAndCategory(String name, String category);
    @Query (value = "SELECT p FROM Product p WHERE p.price > ?1 AND p.price < ?2")
    List<Product> findByPriceRange(double min, double max);
}
