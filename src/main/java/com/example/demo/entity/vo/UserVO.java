package com.example.demo.entity.vo;

import com.example.demo.entity.dto.UserDTO;
import org.springframework.beans.BeanUtils;

public class UserVO {
    private String username;

    private Boolean isAdmin;

    public static UserVO fromUserDTO(UserDTO userDTO) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDTO, userVO);
        return userVO;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
