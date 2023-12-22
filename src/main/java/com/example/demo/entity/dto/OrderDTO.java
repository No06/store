package com.example.demo.entity.dto;

import com.example.demo.entity.*;
import com.example.demo.entity.enums.FieldStatus;
import com.example.demo.entity.enums.OrderStatus;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {
    private Long id;

    private OrderStatus status;

    // 购买用户
    private User user;

    // 评价
    private ProductReview review;

    // 订单商品列表
    private List<OrderItem> orderItems;

    // 订单收货地址
    private UserAddress userAddress;

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

    // 已是否删除
    private FieldStatus isDeleted;

    public static OrderDTO fromPO(Order order) {
        OrderDTO target = new OrderDTO();
        BeanUtils.copyProperties(order, target);
        return target;
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

    public FieldStatus getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(FieldStatus isDeleted) {
        this.isDeleted = isDeleted;
    }
}
