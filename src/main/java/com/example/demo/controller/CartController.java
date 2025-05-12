package com.example.demo.controller;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Goods;
import com.example.demo.entity.dto.cart.UpdateCartDTO;
import com.example.demo.entity.vo.CartVO;
import com.example.demo.service.CartService;
import com.example.demo.service.GoodsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name="购物车接口")
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

    @Operation(summary="获取购物车商品数量")
    @GetMapping("/quantityCount")
    public ResponseEntity<Long> getQuantityCount(@RequestAttribute Long userId) {
        return ResponseEntity.ok(cartService.quantitySumByUserId(userId));
    }

    @Operation(summary="获取购物车商品个数")
    @GetMapping("/count")
    public ResponseEntity<Long> getCount(@RequestAttribute Long userId) {
        return ResponseEntity.ok(cartService.countByUserId(userId));
    }

    @Operation(summary="添加商品到购物车")
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

    @Operation(summary="获取用户购物车列表")
    @GetMapping("/list")
    public ResponseEntity<List<CartVO>> getCartByUserId(@RequestAttribute Long userId) {
        if (userId == null) {
            ResponseEntity.badRequest().body("userId is null");
        }
        return ResponseEntity.ok(cartService.getCartByUserId(userId).stream().map(CartVO::new).toList());
    }

    @Operation(summary="更新购物车商品数量")
    @PatchMapping("/update")
    public ResponseEntity<String> update(
            @RequestBody UpdateCartDTO dto,
            @RequestAttribute Long userId
    ) {
        if (userId == null) {
            return ResponseEntity.badRequest().body("userId is null");
        }
        if (dto == null) {
            return ResponseEntity.badRequest().body("cart is null");
        }
        if (dto.id == null) {
            return ResponseEntity.badRequest().body("id is null");
        }
        if (dto.quantity == null) {
            return ResponseEntity.badRequest().body("quantity is null");
        }
        if (dto.quantity < 1) {
            return ResponseEntity.badRequest().body("quantity is less than 1");
        }
        if (dto.isSelected == null) {
            return ResponseEntity.badRequest().body("isSelected is null");
        }

        Cart cart = cartService.findCartByIdAndUserId(userId, dto.id);
        cart.setQuantity(dto.quantity);
        cart.setIsSelected(dto.isSelected);
        cartService.updateCart(userId, cart);
        return ResponseEntity.ok().build();
    }

    @Operation(summary="移除购物`车商品")
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

    @Operation(summary="清空购物车")
    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(@RequestAttribute Long userId) {
        if (userId == null) {
            ResponseEntity.badRequest().body("userId is null");
        }
        cartService.clearCart(userId);
        return ResponseEntity.ok().build();
    }
}