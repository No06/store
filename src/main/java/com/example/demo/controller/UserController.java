package com.example.demo.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.entity.User;
import com.example.demo.entity.UserAddress;
import com.example.demo.entity.dto.user.UserInfoDTO;
import com.example.demo.entity.dto.user.UserLoginDTO;
import com.example.demo.entity.dto.user.UserRegisterDTO;
import com.example.demo.service.UserAddressService;
import com.example.demo.service.UserService;
import com.example.demo.utils.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="用户接口")
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

    @Operation(summary="注册")
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
        userService.save(new User(dto));
        return ResponseEntity.ok().build();
    }

    @Operation(summary="登录")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO dto) {
        String validateMsg = dto.validate();
        if (validateMsg != null && !validateMsg.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(validateMsg);
        }
        Optional<User> optionalUser = userService.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("username or password is incorrect");
        }
        String token = TokenUtil.generateToken(optionalUser.get());
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate token");
        }
        return ResponseEntity.ok(token);
    }

    @Operation(summary="获取用户信息", description = "需要登录，根据 token 获取用户信息")
    @GetMapping("/info")
    public ResponseEntity<UserInfoDTO> info(@RequestAttribute Long userId) {
        Optional<User> user = userService.findById(userId);
        return user.map(value -> ResponseEntity.ok(new UserInfoDTO(value))).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @Operation(summary="获取 Token 有效性")
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

    @Operation(summary="获取默认地址", description = "需要登录")
    @GetMapping("/get/defaultAddress")
    public ResponseEntity<UserAddress> getDefaultAddress(@RequestAttribute Long userId) {
        UserAddress address = userService.findDefaultAddressById(userId);
        return ResponseEntity.ok(address);
    }

    @Operation(summary="更新默认地址", description = "需要登录")
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

    @Operation(summary="获取所有用户信息", description = "需要登录和管理员权限")
    @GetMapping("/get/all")
    public ResponseEntity<List<User>> getAll(@RequestAttribute Boolean isAdmin) {
        if (isAdmin) {
            return ResponseEntity.ok(userService.findAll());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @DeleteMapping("/remove/byId/{id}")
    @Operation(summary="删除用户", description = "需要登录和管理员权限")
    public ResponseEntity<Void> removeById(@PathVariable Long id, @RequestAttribute Boolean isAdmin) {
        if (isAdmin) {
            userService.removeById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping("/save")
    @Operation(summary="保存用户", description = "需要管理员权限")
    public ResponseEntity<Void> save(@RequestBody User user, @RequestAttribute Boolean isAdmin) {
        if (isAdmin) {
            userService.save(user);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/get/page")
    @Operation(summary="分页获取用户信息", description = "需要登录和管理员权限")
    public ResponseEntity<Page<User>> getPage(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestAttribute Boolean isAdmin
    ) {
        if (isAdmin) {
            return ResponseEntity.ok(userService.findPage(page, size));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
