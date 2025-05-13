package com.example.demo.entity.vo;

import com.example.demo.entity.Carousel;
import com.example.demo.entity.Goods;
import com.example.demo.entity.GoodsPhoto;

import java.math.BigDecimal;
import java.util.List;

public class CarouselVO {
    public final Long id;
    public final Long goodsId;
    public final String goodsName;
    public final BigDecimal goodsPrice;
    public final BigDecimal goodsDiscount;
    public final String photo_url;
    public final String title;
    public final String description;
    public final String subDescription;

    public CarouselVO(Carousel carousel) {
        this.id = carousel.getId();
        Goods goods = carousel.getGoods();
        if (goods != null) {
            this.goodsId = goods.getId();
            this.goodsName = goods.getName();
            this.goodsPrice = goods.getPrice();
            this.goodsDiscount = goods.getDiscount();
            List<GoodsPhoto> photos = goods.getPhotos();
            if (photos != null && !photos.isEmpty()) {
                this.photo_url = photos.getFirst().getPhoto_url();
            } else {
                this.photo_url = null;
            }
        } else {
            this.goodsId = null;
            this.goodsName = null;
            this.goodsPrice = null;
            this.goodsDiscount = null;
            this.photo_url = null;
        }
        this.title = carousel.getTitle();
        this.description = carousel.getDescription();
        this.subDescription = carousel.getSubDescription();
    }
}
