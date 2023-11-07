package com.example.demo.service;

import com.example.demo.entity.dto.CartDTO;
import com.example.demo.exception.CartNotFoundException;
import com.example.demo.exception.ProductNotFoundException;

import java.util.List;

public interface CartService {
    Long quantitySumByUserId(Long userId);

    Long countByUserId(Long userId);

    void addProductToCart(Long userId, Long productId, Integer quantity) throws ProductNotFoundException;

    List<CartDTO> getCartByUserId(Long userId);

    void updateCartQuantity(Long userId, Long productId, Integer quantity) throws CartNotFoundException;

    void deleteCartProduct(Long userId, Long productId);

    void clearCart(Long userId);
}
