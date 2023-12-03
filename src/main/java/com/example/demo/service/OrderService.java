package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.entity.dto.OrderDto;
import com.example.demo.exception.OrderNotFoundException;

import java.util.List;

public interface OrderService {
    void save(OrderDto order, Long userId);
    List<OrderDto> findAll();
    Order findById(Long id) throws OrderNotFoundException;
    List<OrderDto> findAllByUserId(Long userId);
    void deleteById(Long id) throws OrderNotFoundException;
}
