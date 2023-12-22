package com.example.demo.entity.vo;

import com.example.demo.entity.OrderItem;
import com.example.demo.entity.User;
import com.example.demo.entity.UserAddress;
import com.example.demo.entity.dto.OrderDTO;
import com.example.demo.entity.enums.OrderStatus;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

public class OrderVO {
    // 订单状态
    private OrderStatus status;

    // 购买用户
    private User user;

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

    public static OrderVO fromDTO(OrderDTO dto) {
        OrderVO target = new OrderVO();
        BeanUtils.copyProperties(dto, target);
        target.setStatus(dto.getStatus());
        return target;
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
}
