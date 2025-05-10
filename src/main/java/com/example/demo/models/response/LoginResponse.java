package com.example.demo.models.response;

public class LoginResponse {
    public final boolean success;
    public final String data;
    public final String msg;

    public LoginResponse(boolean success, String data, String msg) {
        this.success = success;
        this.data = data;
        this.msg = msg;
    }
}
