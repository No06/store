package com.example.demo.entity.dto;

import com.example.demo.entity.User;
import com.example.demo.entity.UserAddress;
import org.springframework.beans.BeanUtils;

public class UserAddressDTO {
    private Long id;

    private User user;

    // 收货人姓名
    private String name;

    // 收货人电话
    private String phone;

    // 收货人地址
    private String address;

    public static UserAddressDTO fromPO(UserAddress po) {
        UserAddressDTO target = new UserAddressDTO();
        BeanUtils.copyProperties(po, target);
        return target;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
