package com.example.demo.entity.enums;

// 字段状态
public enum FieldStatus {
    DELETED("已删除"),
    AVAILABLE("可用的");

    private final String description; // 中文描述

    FieldStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
