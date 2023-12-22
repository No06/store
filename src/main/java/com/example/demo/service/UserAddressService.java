package com.example.demo.service;

import com.example.demo.entity.dto.UserAddressDTO;
import com.example.demo.exception.UserAddressNotFoundException;
import com.example.demo.exception.UserAddressQuantityAlreadyFullException;
import com.example.demo.exception.UserNotFoundException;

import java.util.List;

public interface UserAddressService {
    void save(UserAddressDTO dto, Long userId) throws UserNotFoundException, UserAddressQuantityAlreadyFullException;
    void saveAll(List<UserAddressDTO> dtoList);
    void removeByIdAndUserId(Long id, Long userId);
    UserAddressDTO findById(Long id) throws UserAddressNotFoundException;
    List<UserAddressDTO> findAllByUserId(Long userId);
}
