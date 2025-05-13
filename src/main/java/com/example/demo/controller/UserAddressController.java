package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.UserAddress;
import com.example.demo.entity.dto.userAddress.UserAddressSaveDTO;
import com.example.demo.entity.vo.UserAddressVO;
import com.example.demo.service.UserAddressService;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name="用户收货地址接口")
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

    @Operation(summary="保存地址信息", description="需要登录\n如果是新建的地址，附加判断是否超过限定数量\n默认数量限制10")
    @PutMapping("/save")
    public ResponseEntity<String> save(@RequestBody UserAddressSaveDTO dto, @RequestAttribute Long userId) {
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // 新建的地址 附加判断是否超过限定数量
        if (dto.id == null) {
            Long count = userAddressService.countAllByUserId(userId);
            if (count > UserAddress.MAX_COUNT) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("number of user address exceeds max count");
            }
        } else if (!userAddressService.existsById(dto.id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("address does not exist");
        }
        userAddressService.save(new UserAddress(dto, userId), userId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary="批量保存地址信息", description="需要登录")
    @PutMapping("/saveAll")
    public ResponseEntity<String> saveAll(@RequestBody List<UserAddress> dtoList, @RequestAttribute Long userId) {
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (dtoList == null || dtoList.isEmpty()) {
            return ResponseEntity.badRequest().body("address list is empty");
        }
        userAddressService.saveAll(dtoList);
        return ResponseEntity.ok().build();
    }

    @Operation(summary="删除地址信息", description="需要登录")
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(
            @RequestParam Long id,
            @RequestAttribute Long userId
    ) {
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
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

    @Operation(summary="获取用户下所有地址", description="需要登录")
    @GetMapping("/get/byUser")
    public ResponseEntity<List<UserAddressVO>> getAllByUserId(@RequestAttribute Long userId) {
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(userAddressService.findAllByUserId(userId).stream().map(UserAddressVO::new).toList());
    }

    @Operation(summary="根据ID查询地址", description="普通用户只能查询自己的，管理员则无限制")
    @GetMapping("/get/byId/{id}")
    public ResponseEntity<UserAddressVO> getById(@PathVariable Long id, @RequestAttribute Long userId, @RequestAttribute Boolean isAdmin) {
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Optional<UserAddress> userAddress = userAddressService.findById(id);
        if (isAdmin) {
            if (userAddress.isPresent()) return ResponseEntity.ok(new UserAddressVO(userAddress.get()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        if (userAddress.isPresent() && userAddress.get().getUser().getId().equals(userId)) {
            return ResponseEntity.ok(new UserAddressVO(userAddress.get()));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
