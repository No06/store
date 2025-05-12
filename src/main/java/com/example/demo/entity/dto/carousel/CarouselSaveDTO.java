package com.example.demo.entity.dto.carousel;

public class CarouselSaveDTO {
    public final Long id;
    public final Long goodsId;
    public final String title;
    public final String description;
    public final String subDescription;

    public CarouselSaveDTO(Long id, Long goodsId, String title, String description, String subDescription) {
        this.id = id;
        this.goodsId = goodsId;
        this.title = title;
        this.description = description;
        this.subDescription = subDescription;
    }
}
