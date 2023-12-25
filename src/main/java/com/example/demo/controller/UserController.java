package com.example.demo.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.entity.dto.UserAddressDTO;
import com.example.demo.entity.dto.UserDTO;
import com.example.demo.entity.vo.UserAddressVO;
import com.example.demo.entity.vo.UserVO;
import com.example.demo.exception.*;
import com.example.demo.service.UserService;
import com.example.demo.utils.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        try {
            userService.register(userDTO);
            return ResponseEntity.ok().build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        try {
            UserDTO foundUser = userService.login(userDTO.getUsername(), userDTO.getPassword());
            String token = TokenUtil.generateToken(foundUser);
            return ResponseEntity.ok(token);
        } catch (UserIncorrectUsernameOrPasswordException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/info")
    public ResponseEntity<UserVO> info(@RequestAttribute Long userId) {
        try {
            return ResponseEntity.ok(UserVO.fromUserDTO(userService.findById(userId)));
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/checkToken")
    public ResponseEntity<?> checkToken(HttpServletRequest request) {
        try {
            TokenUtil.verifyFromRequest(request);
            return ResponseEntity.ok().build();
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/get/defaultAddress")
    public ResponseEntity<UserAddressVO> getDefaultAddress(@RequestAttribute Long userId) {
        UserAddressDTO addressDTO = userService.findDefaultAddressById(userId);
        if (addressDTO == null) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(UserAddressVO.fromDTO(addressDTO));
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

    @GetMapping("/get/all")
    public ResponseEntity<List<UserDTO>> getAll(@RequestAttribute Boolean isAdmin) {
        if (isAdmin) {
            return ResponseEntity.ok(userService.findAll());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @DeleteMapping("/remove/byId/{id}")
    public ResponseEntity<Void> removeById(@PathVariable Long id, @RequestAttribute Boolean isAdmin) {
        if (isAdmin) {
            userService.removeById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping("/save")
    public ResponseEntity<Void> save(@RequestBody UserDTO userDTO, @RequestAttribute Boolean isAdmin) {
        if (isAdmin) {
            userService.save(userDTO);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/get/page")
    public ResponseEntity<Page<UserDTO>> getPage(
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
