package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    User login(String username, String password);
}
