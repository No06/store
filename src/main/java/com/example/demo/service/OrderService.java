package com.example.demo.service;

import com.example.demo.entity.dto.OrderDTO;
import com.example.demo.exception.IllegalOrderStatusException;
import com.example.demo.exception.OrderNotFoundException;

import java.util.List;

public interface OrderService {
    void create(OrderDTO order, Long userId);
    List<OrderDTO> findAll();
    OrderDTO findById(Long id) throws OrderNotFoundException;
    List<OrderDTO> findAllByUserId(Long userId);
    void deleteById(Long id);
    void pay(Long id) throws OrderNotFoundException, IllegalOrderStatusException;
    void delivery(Long id, String expressNumber) throws OrderNotFoundException, IllegalOrderStatusException;
    void receipt(Long id) throws OrderNotFoundException, IllegalOrderStatusException;
    void confirm(Long id) throws OrderNotFoundException, IllegalOrderStatusException;
    void userReview(Long id, String review) throws IllegalOrderStatusException, OrderNotFoundException;
    void sellerReview(Long id, String review) throws OrderNotFoundException;
    void userAdditionReview(Long id, String review) throws OrderNotFoundException;
}
