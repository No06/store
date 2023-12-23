package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.entity.UserAddress;
import com.example.demo.entity.dto.UserAddressDTO;
import com.example.demo.exception.UserAddressNotFoundException;
import com.example.demo.exception.UserAddressQuantityAlreadyFullException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserAddressRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserAddressServiceImpl implements UserAddressService {

    private final UserAddressRepository addressRepository;

    private final UserRepository userRepository;

    @Autowired
    public UserAddressServiceImpl(UserAddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(UserAddressDTO dto, Long userId) throws UserNotFoundException, UserAddressQuantityAlreadyFullException {
        UserAddress userAddress;
        User user = new User(userId);
        // 新建的地址
        if (dto.getId() == null) {
            Long count = addressRepository.countAllByUserId(userId);
            // 判断如果是新建的第一个地址 则设为默认使用
            if (count == null || count == 0) {
                user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("ID: "+userId));
                userAddress = UserAddress.fromDTO(dto);
                user.setDefaultAddress(userAddress);
                userRepository.save(user);
            } else if (count >= UserAddress.MAX_COUNT) {
                throw new UserAddressQuantityAlreadyFullException();
            }
        }
        dto.setUser(user);
        addressRepository.save(UserAddress.fromDTO(dto));
    }

    @Override
    public void saveAll(List<UserAddressDTO> dtoList) {
        addressRepository.saveAll(dtoList.stream().map(UserAddress::fromDTO).toList());
    }

    @Override
    public void removeByIdAndUserId(Long id, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        // 如果user可以找到并且设置的默认地址等于这个地址 则先设为null再继续
        if (user.isPresent() && Objects.equals(user.get().getDefaultAddress().getId(), id)) {
            User u = user.get();
            u.setDefaultAddress(null);
            userRepository.save(u);
        }
        addressRepository.removeByIdAndUserId(id, userId);
    }

    @Override
    public UserAddressDTO findById(Long id) throws UserAddressNotFoundException {
        return UserAddressDTO.fromPO(addressRepository.findById(id).orElseThrow(() -> new UserAddressNotFoundException("ID: "+id)));
    }


    @Override
    public List<UserAddressDTO> findAllByUserId(Long userId) {
        return addressRepository.findAllByUserId(userId).stream().map(UserAddressDTO::fromPO).toList();
    }
}
