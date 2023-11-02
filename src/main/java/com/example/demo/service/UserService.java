package com.example.demo.service;

import com.example.demo.entity.dto.UserDTO;

public interface UserService {
    void register(UserDTO userDTO) throws Exception;
    UserDTO login(String username, String password) throws Exception;
    UserDTO findById(Integer id) throws Exception;
}
