package com.example.demo.entity.enums;

public enum OrderStatus {
    CANCEL("已取消"),
    WAITING_PAY("待付款"),
    PAID("已付款"),
    DELIVERED("已发货"),
    RECEIPTED("已收货"),
    CONFIRMED("已确收"),
    REVIEWED("待评价");

    private final String description; // 中文描述

    // 构造方法
    OrderStatus(String description) {
        this.description = description;
    }

    // getter方法
    public String getDescription() {
        return description;
    }
}
