package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
    private User user;
    // 对应商品
    @ManyToOne
    private Goods goods;
    // 数量
    private Integer quantity;
    // 被选择状态
    @ColumnDefault("1")
    private Boolean isSelected;
}
