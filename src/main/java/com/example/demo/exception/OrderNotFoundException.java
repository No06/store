package com.example.demo.exception;

import com.example.demo.entity.Order;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
