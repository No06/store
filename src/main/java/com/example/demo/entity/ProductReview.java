package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

// 商品评价
@Entity
@DynamicUpdate
@Table(name = "product_reviews")
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Order order;

    // 用户评价
    private String userReview;

    // 卖家评价
    private String sellerReview;

    // 用户追评
    private String userAdditionReview;

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public String getUserAdditionReview() {
        return userAdditionReview;
    }

    public void setUserAdditionReview(String userAdditionEvaluation) {
        this.userAdditionReview = userAdditionEvaluation;
    }
}
