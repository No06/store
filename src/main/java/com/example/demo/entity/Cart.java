package com.example.demo.entity;

import com.example.demo.entity.dto.cart.UpdateCartDTO;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import org.hibernate.annotations.*;

@Entity
@Table(name = "cart")
@DynamicInsert
@DynamicUpdate
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 对应用户
    @ManyToOne
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    // 对应商品
    @ManyToOne
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Goods goods;
    // 数量
    private Integer quantity;
    // 被选择状态
    @ColumnDefault("1")
    private Boolean isSelected;

    public Cart(UpdateCartDTO dto) {
        this.id = dto.id;
        this.quantity = dto.quantity;
        this.isSelected = dto.isSelected;
    }

    public Cart() {

    }
}
