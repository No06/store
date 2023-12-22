package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.dto.UserAddressDTO;
import com.example.demo.entity.dto.UserDTO;
import com.example.demo.exception.*;

public interface UserService {
    void register(UserDTO userDTO) throws UserAlreadyExistsException;
    UserDTO login(String username, String password) throws UserIncorrectUsernameOrPasswordException;
    User findById(Long id) throws UserNotFoundException;
    UserAddressDTO findDefaultAddressById(Long id);
    void updateDefaultUserAddressById(Long addressId, Long userId) throws TokenInfoNotMatchException, UserAddressNotFoundException, IllegalAccessException;
}
