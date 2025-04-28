package com.example.demo.entity.dto.goods;

import com.example.demo.entity.GoodsCategory;

public class GoodsCountByCategoryDTO {
    public final GoodsCategory category;
    public final Long count;

    public GoodsCountByCategoryDTO(GoodsCategory category, Long count) {
        this.category = category;
        this.count = count;
    }
}
