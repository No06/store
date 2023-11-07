package com.example.demo.entity.vo;

import com.example.demo.entity.Product;
import com.example.demo.entity.dto.CartDTO;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public class CartVO {
    private Long id;

    private Product product;

    private Integer quantity;

    private BigDecimal subtotal;

    public static CartVO fromCartDTO(CartDTO cartDTO) {
        CartVO target = new CartVO();
        BeanUtils.copyProperties(cartDTO, target);
        return target;
    }

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
