package com.example.demo.service;

import com.example.demo.entity.dto.UserAddressDTO;
import com.example.demo.entity.dto.UserDTO;
import com.example.demo.exception.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    void register(UserDTO userDTO) throws UserAlreadyExistsException;
    UserDTO login(String username, String password) throws UserIncorrectUsernameOrPasswordException;
    UserDTO findById(Long id) throws UserNotFoundException;
    UserAddressDTO findDefaultAddressById(Long id);
    void updateDefaultUserAddressById(Long addressId, Long userId) throws TokenInfoNotMatchException, UserAddressNotFoundException, IllegalAccessException;
    List<UserDTO> findAll();
    void save(UserDTO userDTO);
    void removeById(Long id);
    Page<UserDTO> findPage(Integer page, Integer size);
}
