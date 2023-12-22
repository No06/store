package com.example.demo.exception;

public class UserAddressNotFoundException extends Exception {
    public UserAddressNotFoundException(String message) {
        super("用户地址未找到，"+message);
    }
}
