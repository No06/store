package com.example.demo.entity.vo;

import com.example.demo.entity.UserAddress;

public class UserAddressVO {
    public final Long id;
    public final String name;
    public final String phone;
    public final String address;

    public UserAddressVO(UserAddress userAddress) {
        this.id = userAddress.getId();
        this.name = userAddress.getName();
        this.phone = userAddress.getPhone();
        this.address = userAddress.getAddress();
    }
}
