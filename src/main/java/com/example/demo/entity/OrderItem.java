package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

// 订单商品项类
@Entity
@Data
@Table(name = "order_items")
public class OrderItem {
    // 商品ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 商品
    @OneToOne
    private Goods goods;

    @OneToOne
    private Order order;

    // 商品数量
    private int quantity;
}
