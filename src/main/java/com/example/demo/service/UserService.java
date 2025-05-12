package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.demo.entity.User;
import com.example.demo.entity.UserAddress;

public interface UserService {
    Optional<User> findById(Long id);

    Optional<User> findByUsername(String name);

    Optional<User> findByUsernameAndPassword(String username, String password);

    UserAddress findDefaultAddressById(Long id);

    void updateDefaultUserAddressById(Long addressId, Long userId);

    List<User> findAll();

    void save(User user);

    void removeById(Long id);

    Page<User> findPage(Integer page, Integer size);
}
