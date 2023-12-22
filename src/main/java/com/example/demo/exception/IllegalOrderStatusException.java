package com.example.demo.exception;

public class IllegalOrderStatusException extends Exception {

    public IllegalOrderStatusException(String message) {
        super("订单状态不合法，"+message);
    }
}
