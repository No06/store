package com.example.demo.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message) {
        super("未找到用户，"+message);
    }
}
