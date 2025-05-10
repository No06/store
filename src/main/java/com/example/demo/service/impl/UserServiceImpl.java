package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.entity.UserAddress;
import com.example.demo.entity.dto.user.UserLoginDTO;
import com.example.demo.entity.dto.user.UserRegisterDTO;
import com.example.demo.models.response.LoginResponse;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.utils.TokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String register(UserRegisterDTO dto) {
        String msg = dto.validate();
        if (msg != null) {
            return msg;
        }
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            return "用户名已存在";
        }
        User user = new User(dto);
        userRepository.save(user);
        return null;
    }

    @Override
    public LoginResponse login(UserLoginDTO dto) {
        String msg = dto.validate();
        if (msg != null) {
            return new LoginResponse(false, null, msg);
        }
        User user = userRepository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (user == null) {
            return new LoginResponse(false, null, "用户名或密码错误");
        }
        String token = TokenUtil.generateToken(user);
        if (token == null || token.isEmpty()) {
            return new LoginResponse(false, null, "Token生成失败");
        }
        return new LoginResponse(true, token, null);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserAddress findDefaultAddressById(Long id) {
        return userRepository.findDefaultAddressById(id);
    }

    @Override
    public void updateDefaultUserAddressById(Long addressId, Long userId) {
        userRepository.updateDefaultAddressById(addressId, userId);
    }

    @Override
     public List<User> findAll() {
         return userRepository.findAll();
     }
 
     @Override
     public void save(User user) {
         userRepository.save(user);
     }
 
     @Override
     public void removeById(Long id) {
         userRepository.removeById(id);
     }
 
     @Override
     public Page<User> findPage(Integer page, Integer size) {
         PageRequest pageable = PageRequest.of(page - 1, size);
         return userRepository.findAll(pageable);
     }
}
