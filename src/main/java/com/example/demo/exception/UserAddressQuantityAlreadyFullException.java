package com.example.demo.exception;

import com.example.demo.entity.UserAddress;

public class UserAddressQuantityAlreadyFullException extends Exception {

    public UserAddressQuantityAlreadyFullException() {
        super("收货地址保存已达上限 "+ UserAddress.MAX_COUNT);
    }
}
