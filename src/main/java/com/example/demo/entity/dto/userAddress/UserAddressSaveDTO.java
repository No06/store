package com.example.demo.entity.dto.userAddress;

public class UserAddressSaveDTO {
    public final Long id;
    public final String name;
    public final String phone;
    public final String address;

    UserAddressSaveDTO(Long id, String name, String phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
}
