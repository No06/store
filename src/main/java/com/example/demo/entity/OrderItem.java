package com.example.demo.entity;

import jakarta.persistence.*;

// 订单商品项类
@Entity
@Table(name = "order_items")
public class OrderItem {
    // 商品ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 商品
    @OneToOne
    private Product product;

    @OneToOne
    private Order order;

    // 商品数量
    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
