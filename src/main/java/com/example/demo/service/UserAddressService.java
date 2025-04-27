package com.example.demo.service;

import com.example.demo.entity.UserAddress;
import com.example.demo.exception.UserAddressNotFoundException;
import com.example.demo.exception.UserAddressQuantityAlreadyFullException;
import com.example.demo.exception.UserNotFoundException;

import java.util.List;

public interface UserAddressService {
    void save(UserAddress address, Long userId) throws UserNotFoundException, UserAddressQuantityAlreadyFullException;
    void saveAll(List<UserAddress> addresses);
    void removeByIdAndUserId(Long id, Long userId);
    UserAddress findById(Long id) throws UserAddressNotFoundException;
    List<UserAddress> findAllByUserId(Long userId);
}
