package com.example.demo.repository;

import com.example.demo.entity.GoodsReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsReviewRepository extends JpaRepository<GoodsReview, Long> {

}
