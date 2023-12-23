package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.entity.UserAddress;
import com.example.demo.entity.dto.UserAddressDTO;
import com.example.demo.entity.dto.UserDTO;
import com.example.demo.exception.UserAddressNotFoundException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserIncorrectUsernameOrPasswordException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserAddressRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserAddressRepository addressRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserAddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void register(UserDTO userDTO) throws UserAlreadyExistsException {
        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            throw new UserAlreadyExistsException();
        }
        User user = User.fromUserDTO(userDTO);
        userRepository.save(user);
    }

    @Override
    public UserDTO login(String username, String password) throws UserIncorrectUsernameOrPasswordException {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            throw new UserIncorrectUsernameOrPasswordException();
        }
        return UserDTO.fromUser(user);
    }

    @Override
    public User findById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("ID: "+id));
    }

    @Override
    public UserAddressDTO findDefaultAddressById(Long id) {
        UserAddress address = userRepository.findDefaultAddressById(id);
        if (address == null) {
            return null;
        }
        return UserAddressDTO.fromPO(address);
    }

    @Override
    public void updateDefaultUserAddressById(Long addressId, Long userId) throws UserAddressNotFoundException, IllegalAccessException {
        UserAddress address = addressRepository.findById(addressId).orElseThrow(() -> new UserAddressNotFoundException("ID: "+addressId));
        if (!Objects.equals(address.getUser().getId(), userId)) {
            throw new IllegalAccessException("与收货地址所归属的用户不匹配");
        }
        userRepository.updateDefaultAddressById(addressId, userId);
    }
}
