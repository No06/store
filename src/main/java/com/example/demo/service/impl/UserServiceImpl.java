package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.entity.UserAddress;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

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
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
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
