package com.example.demo.entity.dto.user;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String username;
    private String password;

    public UserLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String validate() {
        if (username == null || username.isEmpty()) {
            return "用户名不能为空";
        }
        if (password == null || password.isEmpty()) {
            return "密码不能为空";
        }
        return null;
    }
}
