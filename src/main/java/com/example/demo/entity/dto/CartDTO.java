package com.example.demo.entity.dto;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import org.springframework.beans.BeanUtils;

public class CartDTO {
    private Long id;

    private User user;

    private Product product;

    private Integer quantity;

    private Boolean isSelected;

    public static CartDTO fromCart(Cart cart) {
        CartDTO target = new CartDTO();
        BeanUtils.copyProperties(cart, target);
        return target;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean selected) {
        isSelected = selected;
    }
}
