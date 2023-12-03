package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_evaluations")
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 用户评价
    private String userReview;

    // 卖家评价
    private String sellerReview;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserReview() {
        return userReview;
    }

    public void setUserReview(String userReview) {
        this.userReview = userReview;
    }

    public String getSellerReview() {
        return sellerReview;
    }

    public void setSellerReview(String sellerReview) {
        this.sellerReview = sellerReview;
    }
}
