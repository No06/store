package com.example.demo.entity.vo;

import com.example.demo.entity.GoodsCategory;

public class GoodsCategoryWithCountVO {
    public final GoodsCategory category;
    public final Long count;

    public GoodsCategoryWithCountVO(GoodsCategory category, Long count) {
        this.category = category;
        this.count = count;
    }
}
