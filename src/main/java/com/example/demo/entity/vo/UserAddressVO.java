package com.example.demo.entity.vo;

import com.example.demo.entity.dto.UserAddressDTO;
import org.springframework.beans.BeanUtils;

public class UserAddressVO {
    private Long id;

    // 收货人姓名
    private String name;

    // 收货人电话
    private String phone;

    // 收货人地址
    private String address;

    private Boolean isSelected = false;

    public static UserAddressVO fromDTO(UserAddressDTO dto) {
        UserAddressVO target = new UserAddressVO();
        BeanUtils.copyProperties(dto, target);
        return target;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
