package com.example.demo.service.impl;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.entity.dto.CartDTO;
import com.example.demo.exception.CartNotFoundException;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
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
    public void addProductToCart(Long userId, Long productId, Integer quantity) throws ProductNotFoundException {
        // 检查参数是否合法
        if (userId == null || productId == null || quantity == null || quantity < 1) {
            throw new IllegalArgumentException("参数不合法");
        }
        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            throw new ProductNotFoundException("商品不存在");
        }
        Cart cart = cartRepository.findByUserIdAndProductId(userId, productId);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(new User(userId));
            cart.setProduct(product);
            cart.setQuantity(quantity);
            cart.setSubtotal(product.getPrice().multiply(new BigDecimal(quantity)));
        } else {
            cart.setQuantity(cart.getQuantity() + quantity);
            cart.setSubtotal(cart.getSubtotal().add(product.getPrice().multiply(new BigDecimal(quantity))));
        }
        cartRepository.save(cart);
    }

    @Override
    public List<CartDTO> getCartByUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("参数不合法");
        }
        return cartRepository.findByUserId(userId).stream().map(CartDTO::fromCart).toList();
    }

    @Override
    public void updateCartQuantity(Long userId, Long productId, Integer quantity) throws CartNotFoundException {
        if (userId == null || productId == null || quantity == null || quantity < 1) {
            throw new IllegalArgumentException("参数不合法");
        }
        Cart cart = cartRepository.findByUserIdAndProductId(userId, productId);
        if (cart == null) {
            throw new CartNotFoundException("购物车不存在");
        }
        cart.setQuantity(quantity);
        cart.setSubtotal(cart.getProduct().getPrice().multiply(new BigDecimal(quantity)));
        cartRepository.save(cart);
    }

    @Override
    public void deleteCartProduct(Long userId, Long productId) {
        if (userId == null || productId == null) {
            throw new IllegalArgumentException("参数不合法");
        }
        cartRepository.removeByUserIdAndProductId(userId, productId);
    }

    @Override
    public void clearCart(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("参数不合法");
        }
        cartRepository.removeByUserId(userId);
    }
}