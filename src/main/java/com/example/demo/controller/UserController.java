package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;

@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User foundUser = userService.login(user.getUsername(), user.getPassword());
        if (foundUser != null) {
            String token = TokenUtil.generateToken(foundUser);
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("账号或密码错误！");
        }
    }

    @GetMapping("/checkToken")
    public Boolean checkToken(@RequestHeader String token) {
        if (!TokenUtil.verify(token)) {
            new AuthenticationException("JWT: " + token + ", is Expired.").printStackTrace();
            return false;
        }
        return true;
    }
}
