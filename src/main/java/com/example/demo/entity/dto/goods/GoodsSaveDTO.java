package com.example.demo.entity.dto.goods;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.entity.GoodsCategory;
import com.example.demo.entity.GoodsPhoto;

public class GoodsSaveDTO {
    public final Long id;
    public final String name;
    public final BigDecimal price;
    public final BigDecimal discount;
    public final Integer stock;
    public final String description;
    public final GoodsCategory category;
    public final List<GoodsPhoto> photos;

    public GoodsSaveDTO(Long id, String name, BigDecimal price, BigDecimal discount, Integer stock, String description,
            GoodsCategory category, List<GoodsPhoto> photos) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.stock = stock;
        this.description = description;
        this.category = category;
        this.photos = photos;
    }
}
