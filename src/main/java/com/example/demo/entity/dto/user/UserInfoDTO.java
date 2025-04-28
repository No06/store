package com.example.demo.entity.dto.user;

import com.example.demo.entity.User;

import lombok.Data;

@Data
public class UserInfoDTO {
    private Long id;
    private String username;
    private Boolean isAdmin;

    public UserInfoDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.isAdmin = user.getIsAdmin();
    }
}
