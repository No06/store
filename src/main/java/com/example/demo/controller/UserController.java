package com.example.demo.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.entity.User;
import com.example.demo.entity.UserAddress;
import com.example.demo.entity.dto.user.UserInfoDTO;
import com.example.demo.entity.dto.user.UserLoginDTO;
import com.example.demo.entity.dto.user.UserRegisterDTO;
import com.example.demo.entity.response.LoginResponse;
import com.example.demo.exception.*;
import com.example.demo.service.UserService;
import com.example.demo.utils.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterDTO dto) {
        String msg = userService.register(dto);
        if (msg != null) {
            return ResponseEntity.badRequest().body(msg);
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO dto) {
        LoginResponse resp = userService.login(dto);
        if (!resp.success) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp.msg);
        }
        return ResponseEntity.ok(resp.data);
    }

    @GetMapping("/info")
    public ResponseEntity<UserInfoDTO> info(@RequestAttribute Long userId) {
        Optional<User> user = userService.findById(userId);
        if (user.isPresent()) {
            return ResponseEntity.ok(new UserInfoDTO(user.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/checkToken")
    public ResponseEntity<String> checkToken(HttpServletRequest request) {
        String token = TokenUtil.getTokenFromRequest(request);
        try {
            TokenUtil.verify(token);
            return ResponseEntity.ok("Token is valid");
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/get/defaultAddress")
    public ResponseEntity<UserAddress> getDefaultAddress(@RequestAttribute Long userId) {
        UserAddress address = userService.findDefaultAddressById(userId);
        return ResponseEntity.ok(address);
    }

    @PutMapping("/update/defaultAddress")
    public ResponseEntity<String> updateDefaultAddress(@RequestParam Long addressId, @RequestAttribute Long userId) {
        try {
            userService.updateDefaultUserAddressById(addressId, userId);
        } catch (TokenInfoNotMatchException | UserAddressNotFoundException | IllegalAccessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
