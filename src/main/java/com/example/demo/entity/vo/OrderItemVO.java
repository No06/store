package com.example.demo.entity.vo;

import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.entity.dto.OrderItemDTO;
import org.springframework.beans.BeanUtils;

public class OrderItemVO {
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

    public static OrderItemVO fromDTO(OrderItemDTO orderItemDTO) {
        OrderItemVO target = new OrderItemVO();
        BeanUtils.copyProperties(orderItemDTO, target);
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
