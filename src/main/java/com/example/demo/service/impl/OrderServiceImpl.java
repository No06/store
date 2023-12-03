package com.example.demo.service.impl;

import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.entity.dto.OrderDto;
import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;

    @Autowired
    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(OrderDto order, Long userId) {
        User user = new User();
        user.setId(userId);
        order.setUser(user);
        repository.save(Order.fromDTO(order));
    }

    @Override
    public List<OrderDto> findAll() {
        return repository.findAll().stream().map(OrderDto::fromOrder).toList();
    }

    @Override
    public Order findById(Long id) throws OrderNotFoundException {
        return repository.findById(id).orElseThrow(() -> new OrderNotFoundException("未找到ID："+id));
    }

    @Override
    public List<OrderDto> findAllByUserId(Long userId) {
        return repository.findAllByUserId(userId).stream().map(OrderDto::fromOrder).toList();
    }

    @Override
    public void deleteById(Long id) throws OrderNotFoundException {
        Order order = repository.findById(id).orElseThrow(() -> new OrderNotFoundException("未找到ID："+id));
        order.setIsDeleted(1);
    }
}
