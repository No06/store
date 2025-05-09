package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.UserAddress;
import com.example.demo.service.UserAddressService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class UserAddressController {
    private final UserAddressService userAddressService;
    private final UserService userService;

    @Autowired
    public UserAddressController(UserAddressService userAddressService, UserService userService) {
        this.userAddressService = userAddressService;
        this.userService = userService;
    }

    @PutMapping("/save")
    public ResponseEntity<String> save(@RequestBody UserAddress dto, @RequestAttribute Long userId) {
        Long count = userAddressService.countAllByUserId(userId);
        // 新建的地址 附加判断是否超过限定数量
        if (dto.getId() == null && count >= UserAddress.MAX_COUNT) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("number of addresses is full");
        }
        userAddressService.save(dto, userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/saveAll")
    public ResponseEntity<Void> saveAll(@RequestBody List<UserAddress> dtoList) {
        userAddressService.saveAll(dtoList);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(
            @RequestParam Long id,
            @RequestAttribute Long userId
    ) {
        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        User user = optionalUser.get();
        if (user.getDefaultAddress() != null && user.getDefaultAddress().getId().equals(id)) {
           user.setDefaultAddress(null);
           userService.save(user);
        }
        userAddressService.removeById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/byUser")
    public ResponseEntity<List<UserAddress>> getAllByUserId(@RequestAttribute Long userId) {
        List<UserAddress> vos = userAddressService.findAllByUserId(userId);
        return ResponseEntity.ok(vos);
    }

    @GetMapping("/get/byId/{id}")
    public ResponseEntity<UserAddress> getById(@PathVariable Long id) {
        Optional<UserAddress> userAddress = userAddressService.findById(id);
        return userAddress.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
