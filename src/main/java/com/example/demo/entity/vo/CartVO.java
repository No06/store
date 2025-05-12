package com.example.demo.entity.vo;

import com.example.demo.entity.Cart;

import java.math.BigDecimal;

public class CartVO {
    public final Long id;
    public final Long goodsId;
    public final String goodsName;
    public final String goodsDescription;
    public final String goodsPhotoUrl;
    public final BigDecimal goodsPrice;
    public final BigDecimal goodsDiscount;
    public final Integer goodsStock;
    public final Integer quantity;
    public final Boolean isSelected;

    public CartVO(Cart cart) {
        id = cart.getId();
        goodsId = cart.getGoods().getId();
        goodsName = cart.getGoods().getName();
        goodsDescription = cart.getGoods().getDescription();
        goodsPhotoUrl = cart.getGoods().getPhotos().getFirst().getPhoto_url();
        goodsPrice = cart.getGoods().getPrice();
        goodsDiscount = cart.getGoods().getDiscount();
        goodsStock = cart.getGoods().getStock();
        quantity = cart.getQuantity();
        isSelected = cart.getIsSelected();
    }
}
