package com.example.demo.service;

import com.example.demo.entity.Order;

import java.util.List;

public interface OrderService {
    void create(Order order, Long userId);
    List<Order> findAll();
    Order findById(Long id);
    List<Order> findAllByUserId(Long userId);
    void deleteById(Long id);
    void pay(Order order);
    void delivery(Order order, String expressNumber);
    void receipt(Order order);
    void confirm(Order order);
    void userReview(Order order, String review);
    void sellerReview(Order order, String review);
    void userAdditionReview(Order order, String review);
}
