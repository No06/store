package com.example.demo.service.impl;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Goods;
import com.example.demo.entity.User;
import com.example.demo.repository.CartRepository;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart findCartByIdAndUserId(Long id, Long userId) {
        return cartRepository.findByIdAndUserId(id, userId);
    }

    @Override
    public Long quantitySumByUserId(Long userId) {
        return cartRepository.quantitySumByUserId(userId);
    }

    @Override
    public Long countByUserId(Long userId) {
        return cartRepository.countByUserId(userId);
    }

    @Override
    public void addGoodsToCart(Long userId, Long goodsId, Integer quantity) {
        Cart cart = cartRepository.findByUserIdAndGoodsId(userId, goodsId);
        User user = new User(userId);
        Goods goods = new Goods();
        goods.setId(goodsId);

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setGoods(goods);
            cart.setQuantity(quantity);
        } else {
            cart.setQuantity(cart.getQuantity() + quantity);
        }

        cartRepository.save(cart);
    }

    @Override
    public List<Cart> getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public void updateCart(Long userId, Cart cart) {
        User user = new User();
        user.setId(userId);
        cart.setUser(user);
        cartRepository.save(cart);
    }

    @Override
    public void deleteCartGoods(Long userId, Long goodsId) {
        cartRepository.removeByUserIdAndGoodsId(userId, goodsId);
    }

    @Override
    public void clearCart(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("参数不合法");
        }
        cartRepository.removeByUserId(userId);
    }
}
