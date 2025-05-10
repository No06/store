package com.example.demo.service.impl;

import com.example.demo.entity.Order;
import com.example.demo.entity.GoodsReview;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.FieldStatus;
import com.example.demo.entity.enums.OrderStatus;
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
    public Order findById(Long id) {
        return repository.findByIdAndFieldStatus(id, FieldStatus.AVAILABLE);
    }

    @Override
    public List<Order> findAllByUserId(Long userId) {
        return repository.findAllByUserIdAndFieldStatus(userId, FieldStatus.AVAILABLE);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    // 付款
    @Override
    public void pay(Order order) {
        order.setPayTime(LocalDateTime.now());
        order.setStatus(OrderStatus.PAID);
        repository.save(order);
    }

    // 发货
    @Override
    public void delivery(Order order, String expressNumber)  {
        order.setStatus(OrderStatus.DELIVERED);
        order.setExpressNumber(expressNumber);
        repository.save(order);
    }

    // 收货
    @Override
    public void receipt(Order order) {
        order.setStatus(OrderStatus.RECEIPTED);
        repository.save(order);
    }

    // 确收1
    @Override
    public void confirm(Order order) {
        order.setStatus(OrderStatus.CONFIRMED);
        repository.save(order);
    }

    // 用户评价
    @Override
    public void userReview(Order order, String review) {
        order.setStatus(OrderStatus.REVIEWED);
        GoodsReview goodsReview = order.getReview();
        goodsReview.setUserReview(review);
        order.setReview(goodsReview);
        repository.save(order);
    }

    // 卖家评价
    @Override
    public void sellerReview(Order order, String review) {
        GoodsReview goodsReview = order.getReview();
        goodsReview.setSellerReview(review);
        order.setReview(goodsReview);
        repository.save(order);
    }

    // 用户追评
    @Override
    public void userAdditionReview(Order order, String review) {
        GoodsReview goodsReview = order.getReview();
        goodsReview.setUserAdditionReview(review);
        order.setReview(goodsReview);
        repository.save(order);
    }
}
