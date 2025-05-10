package com.example.demo.repository;

import com.example.demo.entity.Goods;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long>, JpaSpecificationExecutor<Goods> {
    List<Goods> findByName(String name);

    @Query("select p from Goods p where p.category.name = ?1")
    List<Goods> findByCategoryName(String categoryName);

    @Query("select p from Goods p where p.name = ?1 and p.category.name = ?2")
    List<Goods> findByNameAndCategoryName(String goodsName, String categoryName);

    @Query ("select p from Goods p where p.price > ?1 and p.price < ?2")
    List<Goods> findByPriceRange(Double min, Double max);

    @EntityGraph(
            attributePaths = {"name", "price", "photos.photo_url"},
            type = EntityGraph.EntityGraphType.FETCH
    )
    @Query("select p from Goods p join p.photos i where i.rank = (select min(i2.rank) from GoodsPhoto i2 where i2.goods.id = p.id)")
    List<Goods> findAllWithMinRankImage();

    // FIXME: 不使用Object[]
    @Query("SELECT p.category, COUNT(p) FROM Goods p GROUP BY p.category")
    List<Object[]> countAllByCategory();

    @Query("SELECT COUNT(p) FROM Goods p WHERE p.category.id = :categoryId")
    Long countByCategoryId(@Param("categoryId") Long categoryId);
}
