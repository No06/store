package com.example.demo.controller;

import com.example.demo.entity.Cart;
import com.example.demo.exception.CartNotFoundException;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.service.CartService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/quantityCount")
    public ResponseEntity<Long> getQuantityCount(@RequestAttribute Long userId) {
        return ResponseEntity.ok(cartService.quantitySumByUserId(userId));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getCount(@RequestAttribute Long userId) {
        return ResponseEntity.ok(cartService.countByUserId(userId));
    }

    @PutMapping("/add")
    public ResponseEntity<?> addProductToCart(
            @RequestParam Long productId,
            @RequestParam Integer quantity,
            @RequestAttribute Long userId
    ) {
        try {
            cartService.addProductToCart(userId, productId, quantity);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Cart>> getCartByUserId(@RequestAttribute Long userId) {
        List<Cart> data = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(data);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateCartQuantity(
            @RequestBody Cart cart,
            @RequestAttribute Long userId
    ) {
        try {
            cartService.updateCart(userId, cart);
        } catch (CartNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCartProduct(
            @RequestParam Long productId,
            @RequestAttribute Long userId
    ) {
        cartService.deleteCartProduct(userId, productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(@RequestAttribute Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok().build();
    }
}