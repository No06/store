package com.example.demo.entity.dto.goods;

public class GoodsSavePhotoDTO {
    public final Long id;
    public final String photo_url;
    public final Integer rank;

    public GoodsSavePhotoDTO(Long id, String photo_url, Integer rank) {
        this.id = id;
        this.photo_url = photo_url;
        this.rank = rank;
    }
}
