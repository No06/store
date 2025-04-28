package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import org.hibernate.annotations.DynamicUpdate;

// 商品评价
@Entity
@DynamicUpdate
@Data
@Table(name = "goods_review")
public class GoodsReview {
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
}
