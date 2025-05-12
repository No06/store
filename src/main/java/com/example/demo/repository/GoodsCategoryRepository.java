package com.example.demo.repository;

import com.example.demo.entity.GoodsCategory;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsCategoryRepository extends JpaRepository<GoodsCategory, Long>, JpaSpecificationExecutor<GoodsCategory> {
    @Transactional
    void removeById(Long id);

    boolean existsByName(String name);

    GoodsCategory findByName(String name);
}
