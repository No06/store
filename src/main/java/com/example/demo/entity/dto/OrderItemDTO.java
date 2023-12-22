package com.example.demo.entity.dto;

import com.example.demo.entity.Order;
import com.example.demo.entity.OrderItem;
import com.example.demo.entity.Product;
import org.springframework.beans.BeanUtils;

public class OrderItemDTO {
    private Long id;

    // 商品
    private Product product;

    private Order order;

    // 商品数量
    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static OrderItemDTO fromPO(OrderItem orderItem) {
        OrderItemDTO target = new OrderItemDTO();
        BeanUtils.copyProperties(orderItem, target);
        return target;
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
