package com.example.demo.repository;

import com.example.demo.entity.Order;
import com.example.demo.entity.enums.FieldStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByIdAndFieldStatus(Long userId, FieldStatus fieldStatus);
    List<Order> findAllByUserIdAndFieldStatus(Long userId, FieldStatus fieldStatus);
}
