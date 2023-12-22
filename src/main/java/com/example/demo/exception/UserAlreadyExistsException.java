package com.example.demo.exception;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException() {
        super("用户已存在");
    }
}
