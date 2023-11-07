package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.entity.dto.UserDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(UserDTO userDTO) throws Exception {
        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            throw new Exception("用户已存在");
        }
        if (userDTO.getAdmin() == null) {
            userDTO.setAdmin(false);
        }
        User user = User.fromUserDTO(userDTO);
        userRepository.save(user);
    }

    @Override
    public UserDTO login(String username, String password) throws Exception {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            throw new Exception("用户名或密码错误");
        }
        return UserDTO.fromUser(user);
    }

    @Override
    public UserDTO findById(Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new Exception("根据ID未找到用户");
        }
        return UserDTO.fromUser(user.get());
    }
}
