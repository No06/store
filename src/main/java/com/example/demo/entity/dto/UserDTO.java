package com.example.demo.entity.dto;

import com.example.demo.entity.User;
import org.springframework.beans.BeanUtils;

public class UserDTO {
    private Long id;

    private String username;

    private String password;

    private Boolean isAdmin;

    public static UserDTO fromPO(User user) {
        UserDTO target = new UserDTO();
        BeanUtils.copyProperties(user, target);
        return target;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
