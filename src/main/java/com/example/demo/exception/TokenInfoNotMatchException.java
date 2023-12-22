package com.example.demo.exception;

public class TokenInfoNotMatchException extends Exception {
    public TokenInfoNotMatchException(String message) {
        super("密钥信息不匹配，"+message);
    }
}
