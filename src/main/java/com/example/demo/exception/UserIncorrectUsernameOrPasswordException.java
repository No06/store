package com.example.demo.exception;

public class UserIncorrectUsernameOrPasswordException extends Exception {

    public UserIncorrectUsernameOrPasswordException() {
        super("用户名或密码错误");
    }
}
