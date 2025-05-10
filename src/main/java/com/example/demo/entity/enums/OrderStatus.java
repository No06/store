package com.example.demo.entity.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    CANCEL("已取消"),
    WAITING_PAY("待付款"),
    PAID("已付款"),
    DELIVERED("已发货"),
    RECEIPTED("已收货"),
    CONFIRMED("已确收"),
    REVIEWED("待评价");

    // getter方法
    private final String description; // 中文描述

    // 构造方法
    OrderStatus(String description) {
        this.description = description;
    }

}
