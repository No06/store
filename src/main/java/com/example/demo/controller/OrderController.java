package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/getByUserId")
    public ResponseEntity<List<Order>> getAllByUserId(@RequestAttribute Long userId) {
        return ResponseEntity.ok(service.findAllByUserId(userId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Order>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/save")
    public ResponseEntity<Object> save(@RequestBody Order order, @RequestAttribute Long userId) {
        service.create(order, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<Object> deleteById(@RequestParam Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
