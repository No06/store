package com.example.demo.controller;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Goods;
import com.example.demo.service.CartService;
import com.example.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final GoodsService goodsService;

    @Autowired
    public CartController(CartService cartService, GoodsService goodsService) {
        this.cartService = cartService;
        this.goodsService = goodsService;
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
    public ResponseEntity<String> addGoodsToCart(
            @RequestParam Long goodsId,
            @RequestParam Integer quantity,
            @RequestAttribute Long userId
    ) {
        if (userId == null) {
            return ResponseEntity.badRequest().body("userId is null");
        }
        if (goodsId == null) {
            return ResponseEntity.badRequest().body("goodsId is null");
        }
        if (quantity == null) {
            return ResponseEntity.badRequest().body("quantity is null");
        }
        Optional<Goods> result = goodsService.findById(goodsId);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("goods not found");
        }
        cartService.addGoodsToCart(userId, goodsId, quantity);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Cart>> getCartByUserId(@RequestAttribute Long userId) {
        if (userId == null) {
            ResponseEntity.badRequest().body("userId is null");
        }
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateCartQuantity(
            @RequestBody Cart cart,
            @RequestAttribute Long userId
    ) {
        if (userId == null) {
            return ResponseEntity.badRequest().body("userId is null");
        }
        if (cart == null) {
            return ResponseEntity.badRequest().body("cart is null");
        }

        Long goodsId = cart.getGoods().getId();
        if (goodsId == null) {
            return ResponseEntity.badRequest().body("goods id is null");
        }

        Integer quantity = cart.getQuantity();
        if (quantity == null) {
            return ResponseEntity.badRequest().body("quantity is null");
        }
        if (quantity < 1) {
            return ResponseEntity.badRequest().body("quantity is less than 1");
        }

        cartService.updateCart(userId, cart);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCartGoods(
            @RequestParam Long goodsId,
            @RequestAttribute Long userId
    ) {
        if (userId == null) {
            ResponseEntity.badRequest().body("userId is null");
        }
        if (goodsId == null) {
            ResponseEntity.badRequest().body("goodsId is null");
        }
        cartService.deleteCartGoods(userId, goodsId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(@RequestAttribute Long userId) {
        if (userId == null) {
            ResponseEntity.badRequest().body("userId is null");
        }
        cartService.clearCart(userId);
        return ResponseEntity.ok().build();
    }
}