package com.example.demo.entity;

import com.example.demo.entity.dto.OrderDto;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

// 订单类
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * 订单状态
     *
     * 0 已取消
     * 1 未付款
     * 2 待发货
     * 3 待收货
     * 4 待确收
     * 5 待评价
     * 6 已完成
    */
    @Column(length = 1, nullable = false)
    @ColumnDefault("1")
    private Integer status;

    // 购买用户
    @OneToOne
    private User user;

    // 评价
    @OneToOne
    private ProductReview review;

    // 订单总金额
    private BigDecimal totalPrice;

    // 订单商品列表
    @OneToMany
    private List<OrderItem> orderItems;

    // 订单收货地址
    @OneToOne
    private UserAddress userAddress;

    // 订单创建时间
    private Date createTime;

    // 订单取消时间
    private Date cancelTime;

    // 订单支付时间
    private Date payTime;

    // 订单发货时间
    private Date deliverTime;

    // 订单收货时间
    private Date receiveTime;

    // 订单评价时间
    private Date reviewTime;

    // 订单完成时间
    private Date finishTime;

    /*
    * 已是否删除
    * 0 正常
    * 1 删除
    */
    @Column(length = 1, nullable = false)
    @ColumnDefault("0")
    private Integer isDeleted;

    public static Order fromDTO(OrderDto dto) {
        Order target = new Order();
        BeanUtils.copyProperties(dto, target);
        return target;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
