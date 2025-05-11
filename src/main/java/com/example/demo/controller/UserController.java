package com.example.demo.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.entity.User;
import com.example.demo.entity.UserAddress;
import com.example.demo.entity.dto.user.UserInfoDTO;
import com.example.demo.entity.dto.user.UserLoginDTO;
import com.example.demo.entity.dto.user.UserRegisterDTO;
import com.example.demo.models.response.LoginResponse;
import com.example.demo.service.UserAddressService;
import com.example.demo.service.UserService;
import com.example.demo.utils.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="用户接口", description="用户相关API")
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserAddressService userAddressService;

    @Autowired
    public UserController(UserService userService, UserAddressService userAddressService) {
        this.userService = userService;
        this.userAddressService = userAddressService;
    }

    @Operation(summary="用户注册接口")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterDTO dto) {
        String msg = dto.validate();
        if (msg != null) {
            return ResponseEntity.badRequest().body(msg);
        }
        Optional<User> user = userService.findByUsername(dto.getUsername());
        if (user.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }
        return ResponseEntity.ok(null);
    }

    @Operation(summary="用户登录接口")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO dto) {
        LoginResponse resp = userService.login(dto);
        if (!resp.success) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp.msg);
        }
        return ResponseEntity.ok(resp.data);
    }

    @Operation(summary="获取用户信息")
    @GetMapping("/info")
    public ResponseEntity<UserInfoDTO> info(@RequestAttribute Long userId) {
        Optional<User> user = userService.findById(userId);
        return user.map(value -> ResponseEntity.ok(new UserInfoDTO(value))).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @Operation(summary="检查 Token")
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

    @Operation(summary="获取默认地址")
    @GetMapping("/get/defaultAddress")
    public ResponseEntity<UserAddress> getDefaultAddress(@RequestAttribute Long userId) {
        UserAddress address = userService.findDefaultAddressById(userId);
        return ResponseEntity.ok(address);
    }

    @Operation(summary="更新默认地址")
    @PutMapping("/update/defaultAddress")
    public ResponseEntity<String> updateDefaultAddress(@RequestParam Long addressId, @RequestAttribute Long userId) {
        Optional<UserAddress> userAddress = userAddressService.findById(addressId);
        if (userAddress.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("address does not exist");
        }
        Optional<User> user = userService.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user does not exist");
        }
        if (!userAddress.get().getUser().getId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User is not in this address");
        }
        userService.updateDefaultUserAddressById(addressId, userId);
        return ResponseEntity.ok().build();
    }
}
