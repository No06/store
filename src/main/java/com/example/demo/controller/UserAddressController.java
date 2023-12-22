package com.example.demo.controller;

import com.example.demo.entity.dto.UserAddressDTO;
import com.example.demo.entity.vo.UserAddressVO;
import com.example.demo.exception.UserAddressNotFoundException;
import com.example.demo.exception.UserAddressQuantityAlreadyFullException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class UserAddressController {
    private final UserAddressService userAddressService;

    @Autowired
    public UserAddressController(UserAddressService userAddressService) {
        this.userAddressService = userAddressService;
    }

    @PutMapping("/save")
    public ResponseEntity<String> save(@RequestBody UserAddressDTO dto, @RequestAttribute Long userId) {
        try {
            userAddressService.save(dto, userId);
        } catch (UserNotFoundException | UserAddressQuantityAlreadyFullException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/saveAll")
    public ResponseEntity<Void> saveAll(@RequestBody List<UserAddressDTO> dtoList) {
        userAddressService.saveAll(dtoList);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(
            @RequestParam Long id,
            @RequestAttribute Long userId
    ) {
        userAddressService.removeByIdAndUserId(id, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/byUser")
    public ResponseEntity<List<UserAddressVO>> getAllByUserId(@RequestAttribute Long userId) {
        List<UserAddressVO> vos = userAddressService.findAllByUserId(userId).stream().map(UserAddressVO::fromDTO).toList();
        return ResponseEntity.ok(vos);
    }

    @GetMapping("/get/byId/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(UserAddressVO.fromDTO(userAddressService.findById(id)));
        } catch (UserAddressNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
