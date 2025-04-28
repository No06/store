package com.example.demo.entity;

import com.example.demo.entity.enums.FieldStatus;
import com.example.demo.entity.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDateTime;
import java.util.List;

// 订单类
@Entity
@DynamicUpdate
@Table(name = "orders")
@Data
@SQLDelete(sql = "update order set fieldStatus = 1 where id = ?")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @NotNull
    @ColumnDefault("1")
    private OrderStatus status;

    // 购买用户
    @OneToOne
    @NotNull
    private User user;

    // 评价
    @OneToOne
    private GoodsReview review;

    // 订单商品列表
    @OneToMany
    @NotNull
    private List<OrderItem> orderItems;

    // 订单收货地址
    @OneToOne
    private UserAddress userAddress;

    // 快递单号
    private String expressNumber;

    // 订单创建时间
    private LocalDateTime createTime;

    // 订单取消时间
    private LocalDateTime cancelTime;

    // 订单支付时间
    private LocalDateTime payTime;

    // 订单发货时间
    private LocalDateTime deliverTime;

    // 订单收货时间
    private LocalDateTime receiveTime;

    // 订单评价时间
    private LocalDateTime reviewTime;

    // 订单完成时间
    private LocalDateTime finishTime;

    /*
    * 已是否删除
    * 0 正常
    * 1 删除
    */
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    @ColumnDefault("0")
    private FieldStatus fieldStatus;
}
