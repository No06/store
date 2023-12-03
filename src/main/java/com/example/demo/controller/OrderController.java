package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.dto.OrderDto;
import com.example.demo.entity.vo.OrderVO;
import com.example.demo.exception.OrderNotFoundException;
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
    public ResponseEntity<List<OrderVO>> getAllByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok(service.findAllByUserId(userId).stream().map(OrderVO::fromDTO).toList());
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderVO>> getAll() {
        return ResponseEntity.ok(service.findAll().stream().map(OrderVO::fromDTO).toList());
    }

    @PutMapping("/save")
    public ResponseEntity<Object> save(@RequestBody OrderDto order, @RequestAttribute Long userId) {
        service.save(order, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<Object> deleteById(@RequestParam Long id) {
        try {
            service.deleteById(id);
        } catch (OrderNotFoundException e) {
            ResponseEntity.badRequest().body(id);
        }
        return ResponseEntity.ok().build();
    }
}
