package com.example.demo.repository;

import com.example.demo.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT SUM(c.quantity) FROM Cart c WHERE c.user.id = ?1")
    Long quantitySumByUserId(Long userId);

    Long countByUserId(Long userId);

    Cart findByUserIdAndProductId(Long userId, Long productId);

    List<Cart> findByUserId(Long userId);

    void removeByUserIdAndProductId(Long userId, Long productId);

    void removeByUserId(Long userId);
}
