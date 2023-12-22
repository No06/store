package com.example.demo.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.entity.User;
import com.example.demo.entity.dto.UserDTO;
import com.example.demo.entity.vo.UserAddressVO;
import com.example.demo.entity.vo.UserVO;
import com.example.demo.exception.*;
import com.example.demo.service.UserService;
import com.example.demo.utils.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
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

    @GetMapping("/info/{id}")
    public ResponseEntity<?> info(@PathVariable Long id, @RequestHeader String token) {
        try {
            TokenUtil.verify(token);
            User user = userService.findById(id);
            UserVO vo = new UserVO();
            BeanUtils.copyProperties(user, vo);
            return ResponseEntity.ok(vo);
        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
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
        return ResponseEntity.ok(UserAddressVO.fromDTO(userService.findDefaultAddressById(userId)));
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
