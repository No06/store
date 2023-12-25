package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findByName(String name);

    @Query("select p from Product p where p.category.name = ?1")
    List<Product> findByCategoryName(String categoryName);

    @Query("select p from Product p where p.name = ?1 and p.category.name = ?2")
    List<Product> findByNameAndCategoryName(String productName, String categoryName);

    @Query ("select p from Product p where p.price > ?1 and p.price < ?2")
    List<Product> findByPriceRange(Double min, Double max);

    @EntityGraph(
            attributePaths = {"name", "price", "images.image_url"},
            type = EntityGraph.EntityGraphType.FETCH
    )
    @Query("select p from Product p join p.images i where i.rank = (select min(i2.rank) from ProductImage i2 where i2.product.id = p.id)")
    List<Product> findAllWithMinRankImage();

    // FIXME: 不使用Object[]
    @Query("SELECT p.category, COUNT(p) FROM Product p GROUP BY p.category")
    List<Object[]> countAllByCategory();

    @Query("SELECT COUNT(p) FROM Product p WHERE p.category.id = :categoryId")
    Long countByCategoryId(@Param("categoryId") Long categoryId);
}
