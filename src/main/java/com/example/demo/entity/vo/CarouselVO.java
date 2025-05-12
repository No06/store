package com.example.demo.entity.vo;

import com.example.demo.entity.Carousel;

import java.math.BigDecimal;

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
        this.goodsId = carousel.getGoods().getId();
        this.goodsName = carousel.getGoods().getName();
        this.goodsPrice = carousel.getGoods().getPrice();
        this.goodsDiscount = carousel.getGoods().getDiscount();
        this.photo_url = carousel.getGoods().getPhotos().getFirst().getPhoto_url();
        this.title = carousel.getTitle();
        this.description = carousel.getDescription();
        this.subDescription = carousel.getSubDescription();
    }
}
