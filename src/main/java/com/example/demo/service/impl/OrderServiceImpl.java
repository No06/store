package com.example.demo.service.impl;

import com.example.demo.entity.Order;
import com.example.demo.entity.GoodsReview;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.FieldStatus;
import com.example.demo.entity.enums.OrderStatus;
import com.example.demo.exception.IllegalOrderStatusException;
import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;

    @Autowired
    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Order order, Long userId) {
        User user = new User();
        user.setId(userId);
        order.setCreateTime(LocalDateTime.now());
        order.setUser(user);
        repository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public Order findById(Long id) throws OrderNotFoundException {
        Order order = repository.findByIdAndFieldStatus(id, FieldStatus.AVAILABLE);
        if (order == null) {
            throw new OrderNotFoundException("ID: " + id);
        }
        return order;
    }

    @Override
    public List<Order> findAllByUserId(Long userId) {
        return repository.findAllByUserIdAndFieldStatus(userId, FieldStatus.AVAILABLE);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    // 更新订单状态
    private Order updateOrderStatus(Long id, OrderStatus toStatus) throws IllegalOrderStatusException, OrderNotFoundException {
        Order order = repository.findById(id).orElseThrow(() -> new OrderNotFoundException("ID: "+id));
        int index = toStatus.ordinal();
        if (index > 0 && order.getStatus() == OrderStatus.values()[index - 1]) {
            order.setStatus(toStatus);
        } else {
            throw new IllegalOrderStatusException("订单状态需要："+OrderStatus.WAITING_PAY.getDescription());
        }
        return order;
    }

    // 付款
    @Override
    public void pay(Long id) throws OrderNotFoundException, IllegalOrderStatusException {
        Order order = updateOrderStatus(id, OrderStatus.PAID);
        repository.save(order);
    }

    // 发货
    @Override
    public void delivery(Long id, String expressNumber) throws OrderNotFoundException, IllegalOrderStatusException {
        Order order = updateOrderStatus(id, OrderStatus.DELIVERED);
        order.setExpressNumber(expressNumber);
        repository.save(order);
    }

    // 收货
    @Override
    public void receipt(Long id) throws OrderNotFoundException, IllegalOrderStatusException {
        Order order = updateOrderStatus(id, OrderStatus.RECEIPTED);
        repository.save(order);
    }

    // 确收1
    @Override
    public void confirm(Long id) throws OrderNotFoundException, IllegalOrderStatusException {
        Order order = updateOrderStatus(id, OrderStatus.CONFIRMED);
        repository.save(order);
    }

    // 用户评价
    @Override
    public void userReview(Long id, String review) throws IllegalOrderStatusException, OrderNotFoundException {
        Order order = updateOrderStatus(id, OrderStatus.REVIEWED);
        GoodsReview goodsReview = order.getReview();
        goodsReview.setUserReview(review);
        order.setReview(goodsReview);
        repository.save(order);
    }

    // 卖家评价
    @Override
    public void sellerReview(Long id, String review) throws OrderNotFoundException {
        Order order = repository.findById(id).orElseThrow(() -> new OrderNotFoundException("ID: "+id));
        GoodsReview goodsReview = order.getReview();
        goodsReview.setSellerReview(review);
        order.setReview(goodsReview);
        repository.save(order);
    }

    // 用户追评
    @Override
    public void userAdditionReview(Long id, String review) throws OrderNotFoundException {
        Order order = repository.findById(id).orElseThrow(() -> new OrderNotFoundException("ID: "+id));
        GoodsReview goodsReview = order.getReview();
        goodsReview.setUserAdditionReview(review);
        order.setReview(goodsReview);
        repository.save(order);
    }
}
