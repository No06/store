package com.example.demo.entity.dto.goods;

import com.example.demo.entity.Goods;
import com.example.demo.entity.GoodsCategory;
import com.example.demo.entity.GoodsPhoto;

import java.math.BigDecimal;
import java.util.List;

public class GoodsShowItemDTO {
    public final Long id;
    public final String name;
    public final BigDecimal price;
    public final BigDecimal discount;
    public final Integer stock;
    public final GoodsCategory category;
    public final GoodsPhoto photo;

    public GoodsShowItemDTO(Long id, String name, BigDecimal price, BigDecimal discount, Integer stock, GoodsCategory category, GoodsPhoto photo) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.stock = stock;
        this.category = category;
        this.photo = photo;
    }

    public GoodsShowItemDTO(Goods goods) {
        this.id = goods.getId();
        this.name = goods.getName();
        this.price = goods.getPrice();
        this.discount = goods.getDiscount();
        this.stock = goods.getStock();
        this.category = goods.getCategory();
        List<GoodsPhoto> photos = goods.getPhotos();
        if (photos != null && !photos.isEmpty()) {
            this.photo = photos.getFirst();
        } else {
            this.photo = null;
        }
    }
}
