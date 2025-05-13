package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.entity.UserAddress;
import com.example.demo.repository.UserAddressRepository;
import com.example.demo.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAddressServiceImpl implements UserAddressService {

    private final UserAddressRepository addressRepository;

    @Autowired
    public UserAddressServiceImpl(UserAddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void save(UserAddress address, Long userId) {
        User user = new User(userId);
        address.setUser(user);
        addressRepository.save(address);
    }

    @Override
    public void saveAll(List<UserAddress> addresses) {
        addressRepository.saveAll(addresses);
    }

    @Override
    public void removeById(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public Optional<UserAddress> findById(Long id) {
        return addressRepository.findById(id);
    }


    @Override
    public List<UserAddress> findAllByUserId(Long userId) {
        return addressRepository.findAllByUserId(userId);
    }

    @Override
    public Long countAllByUserId(Long userId) {
        return addressRepository.countAllByUserId(userId);
    }

    @Override
    public Boolean existsById(Long id) {
        return addressRepository.existsById(id);
    }
}
