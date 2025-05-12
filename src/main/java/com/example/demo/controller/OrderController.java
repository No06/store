package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="订单接口 (未完成)", description = "未完成")
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @Operation(summary="获取用户订单")
    @GetMapping("/getByUserId")
    public ResponseEntity<List<Order>> getAllByUserId(@RequestAttribute Long userId) {
        return ResponseEntity.ok(service.findAllByUserId(userId));
    }

    @Operation(summary="获取所有订单")
    @GetMapping("/getAll")
    public ResponseEntity<List<Order>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary="保存订单")
    @PutMapping("/save")
    public ResponseEntity<Void> save(@RequestBody Order order, @RequestAttribute Long userId) {
        service.create(order, userId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary="删除订单")
    @DeleteMapping("/deleteById")
    public ResponseEntity<Void> deleteById(@RequestParam Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
