package com.example.demo.entity.vo;

import com.example.demo.entity.OrderItem;
import com.example.demo.entity.ProductReview;
import com.example.demo.entity.User;
import com.example.demo.entity.UserAddress;
import com.example.demo.entity.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderVO {
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
    private String status;

    // 购买用户
    private User user;

    // 评价
    private ProductReview review;

    // 订单总金额
    private BigDecimal totalPrice;

    // 订单商品列表
    private List<OrderItem> orderItems;

    // 订单收货地址
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

    // 已是否删除
    private Integer isDeleted;

    public static OrderVO fromDTO(OrderDto dto) {
        OrderVO target = new OrderVO();
        BeanUtils.copyProperties(dto, target);
        target.setStatus(statusString(dto.getStatus()));
        return target;
    }

    public static String statusString(Integer status) {
        switch (status) {
            case 0 -> {
                return "已取消";
            }
            case 1 -> {
                return "未付款";
            }
            case 2 -> {
                return "待发货";
            }
            case 3 -> {
                return "待收货";
            }
            case 4 -> {
                return "待确收";
            }
            case 5 -> {
                return "待评价";
            }
            case 6 -> {
                return "已完成";
            }
            default -> {
                return "未知状态";
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
