package com.example.demo.service.impl;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Goods;
import com.example.demo.entity.User;
import com.example.demo.exception.GoodsNotFoundException;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final GoodsRepository goodsRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, GoodsRepository goodsRepository) {
        this.cartRepository = cartRepository;
        this.goodsRepository = goodsRepository;
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
    public void addGoodsToCart(Long userId, Long goodsId, Integer quantity) throws GoodsNotFoundException {
        // 检查参数是否合法
        if (userId == null || goodsId == null || quantity == null || quantity < 1) {
            throw new IllegalArgumentException("参数不合法");
        }
        Goods goods = goodsRepository.findById(goodsId).orElse(null);
        if (goods == null) {
            throw new GoodsNotFoundException("商品不存在");
        }
        Cart cart = cartRepository.findByUserIdAndGoodsId(userId, goodsId);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(new User(userId));
            cart.setGoods(goods);
            cart.setQuantity(quantity);
        } else {
            cart.setQuantity(cart.getQuantity() + quantity);
        }
        cartRepository.save(cart);
    }

    @Override
    public List<Cart> getCartByUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("参数不合法");
        }
        return cartRepository.findByUserId(userId);
    }

    @Override
    public void updateCart(Long userId, Cart cart) {
        Integer quantity = cart.getQuantity();
        Long goodsId = cart.getGoods().getId();
        if (userId == null || goodsId == null || quantity == null || quantity < 1) {
            throw new IllegalArgumentException("参数不合法");
        }
        User user = new User();
        user.setId(userId);
        cart.setUser(user);
        cartRepository.save(cart);
    }

    @Override
    public void deleteCartGoods(Long userId, Long goodsId) {
        if (userId == null || goodsId == null) {
            throw new IllegalArgumentException("参数不合法");
        }
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
