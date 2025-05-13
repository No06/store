package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

// 用户地址类
@Entity
@DynamicUpdate
@Data
@Table(name = "user_address")
public class UserAddress {
    public static Integer MAX_COUNT = 10; // 用户收货地址存储上限

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    // 收货人姓名
    @NotNull
    @Column(length = 50)
    private String name;

    // 收货人电话
    @NotNull
    @Column(length = 20)
    private String phone;

    // 收货人地址
    @NotNull
    @Column(length = 200)
    private String address;
}
