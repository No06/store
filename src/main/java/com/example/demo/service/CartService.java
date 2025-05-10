package com.example.demo.service;

import com.example.demo.entity.Cart;

import java.util.List;

public interface CartService {
    Long quantitySumByUserId(Long userId);

    Long countByUserId(Long userId);

    void addGoodsToCart(Long userId, Long goodsId, Integer quantity);

    List<Cart> getCartByUserId(Long userId);

    void updateCart(Long userId, Cart cartDTO);

    void deleteCartGoods(Long userId, Long goodsId);

    void clearCart(Long userId);
}
