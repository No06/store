package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserAddress;
import com.example.demo.exception.*;

public interface UserService {
    void register(User user) throws UserAlreadyExistsException;
    User login(String username, String password) throws UserIncorrectUsernameOrPasswordException;
    User findById(Long id) throws UserNotFoundException;
    UserAddress findDefaultAddressById(Long id);
    void updateDefaultUserAddressById(Long addressId, Long userId) throws TokenInfoNotMatchException, UserAddressNotFoundException, IllegalAccessException;
}
