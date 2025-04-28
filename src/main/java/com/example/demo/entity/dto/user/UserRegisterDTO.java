package com.example.demo.entity.dto.user;

import lombok.Data;

@Data
public class UserRegisterDTO {
    public UserRegisterDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String username;
    private String password;

    public String validate() {
        if (username == null || username.isEmpty()) {
            return "用户名不能为空";
        }
        if (password == null || password.isEmpty()) {
            return "密码不能为空";
        }
        if (username.length() < 3 || username.length() > 20) {
            return "用户名长度必须在3到20个字符之间";
        }
        if (password.length() < 6 || password.length() > 20) {
            return "密码长度必须在6到20个字符之间";
        }
        return null;
    }
}
