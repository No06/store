package com.example.demo.entity.vo;

import com.example.demo.entity.Cart;
import com.example.demo.entity.GoodsPhoto;

import java.math.BigDecimal;
import java.util.List;

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
        List<GoodsPhoto> photos = cart.getGoods().getPhotos();
        if (photos != null && !photos.isEmpty()) {
            goodsPhotoUrl = photos.getFirst().getPhoto_url();
        } else {
            goodsPhotoUrl = null;
        }
        goodsPrice = cart.getGoods().getPrice();
        goodsDiscount = cart.getGoods().getDiscount();
        goodsStock = cart.getGoods().getStock();
        quantity = cart.getQuantity();
        isSelected = cart.getIsSelected();
    }
}
