package com.example.demo.entity;

import com.example.demo.entity.dto.CartDTO;
import com.example.demo.entity.dto.OrderDTO;
import com.example.demo.entity.enums.FieldStatus;
import com.example.demo.entity.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

// 订单类
@Entity
@DynamicUpdate
@Table(name = "orders")
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
    private ProductReview review;

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

    public static Order fromDTO(OrderDTO dto) {
        Order target = new Order();
        BeanUtils.copyProperties(dto, target);
        return target;
    }

    public static Order fromCartDTO(CartDTO dto) {
        Order order = new Order();
        order.setUser(dto.getUser());
        return order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProductReview getReview() {
        return review;
    }

    public void setReview(ProductReview review) {
        this.review = review;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(LocalDateTime cancelTime) {
        this.cancelTime = cancelTime;
    }

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }

    public LocalDateTime getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(LocalDateTime deliverTime) {
        this.deliverTime = deliverTime;
    }

    public LocalDateTime getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(LocalDateTime receiveTime) {
        this.receiveTime = receiveTime;
    }

    public LocalDateTime getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(LocalDateTime reviewTime) {
        this.reviewTime = reviewTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public FieldStatus getFieldStatus() {
        return fieldStatus;
    }

    public void setFieldStatus(FieldStatus isDeleted) {
        this.fieldStatus = isDeleted;
    }
}
