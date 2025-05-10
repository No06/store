package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.example.demo.entity.User;
import com.example.demo.entity.UserAddress;
import com.example.demo.entity.dto.user.UserLoginDTO;
import com.example.demo.entity.dto.user.UserRegisterDTO;
import com.example.demo.models.response.LoginResponse;

public interface UserService {
    String register(UserRegisterDTO dto);

    LoginResponse login(UserLoginDTO dto);

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String name);

    UserAddress findDefaultAddressById(Long id);

    void updateDefaultUserAddressById(Long addressId, Long userId);

    List<User> findAll();

    void save(User user);

    void removeById(Long id);

    Page<User> findPage(Integer page, Integer size);
}
