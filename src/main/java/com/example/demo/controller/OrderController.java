package com.example.demo.controller;

import com.example.demo.entity.dto.OrderDTO;
import com.example.demo.entity.vo.OrderVO;
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
    public ResponseEntity<List<OrderVO>> getAllByUserId(@RequestAttribute Long userId) {
        return ResponseEntity.ok(service.findAllByUserId(userId).stream().map(OrderVO::fromDTO).toList());
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderVO>> getAll() {
        return ResponseEntity.ok(service.findAll().stream().map(OrderVO::fromDTO).toList());
    }

    @PutMapping("/save")
    public ResponseEntity<Object> save(@RequestBody OrderDTO order, @RequestAttribute Long userId) {
        service.create(order, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<Object> deleteById(@RequestParam Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
