package com.example.demo.entity.dto.goods;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.entity.GoodsCategory;

public class GoodsSaveDTO {
    public final Long id;
    public final String name;
    public final BigDecimal price;
    public final BigDecimal discount;
    public final Integer stock;
    public final String description;
    public final GoodsCategory category;
    public final List<GoodsSavePhotoDTO> photos;

    public GoodsSaveDTO(Long id, String name, BigDecimal price, BigDecimal discount, Integer stock, String description,
            GoodsCategory category, List<GoodsSavePhotoDTO> photos) {
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
