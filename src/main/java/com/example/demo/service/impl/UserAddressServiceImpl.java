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
        User user = new User(userId);
        Long count = addressRepository.countAllByUserId(userId);
        // 新建的地址 附加判断是否超过限定数量
        if (dto.getId() == null && count >= UserAddress.MAX_COUNT) {
            // 抛出用户收货地址数量已满异常
            throw new UserAddressQuantityAlreadyFullException();
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
        if (user.isPresent() && user.get().getDefaultAddress() != null && Objects.equals(user.get().getDefaultAddress().getId(), id)) {
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
