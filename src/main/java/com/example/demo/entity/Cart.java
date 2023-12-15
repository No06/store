package com.example.demo.entity;

import com.example.demo.entity.dto.CartDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 对应用户
    @ManyToOne
    private User user;
    // 对应商品
    @ManyToOne
    private Product product;
    // 数量
    private Integer quantity;
    // 被选择状态
    @Column(nullable = false)
    @ColumnDefault("1")
    private Boolean isSelected;

    public static Cart fromDTO(CartDTO dto) {
        Cart target = new Cart();
        BeanUtils.copyProperties(dto, target);
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
