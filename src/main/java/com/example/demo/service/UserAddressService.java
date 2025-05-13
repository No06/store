package com.example.demo.service;

import com.example.demo.entity.UserAddress;

import java.util.List;
import java.util.Optional;

public interface UserAddressService {
    void save(UserAddress address, Long userId);
    void saveAll(List<UserAddress> addresses);
    void removeById(Long id);
    Optional<UserAddress> findById(Long id);
    List<UserAddress> findAllByUserId(Long userId);
    Long countAllByUserId(Long userId);
    Boolean existsById(Long id);
}
