package com.example.demo.service;

import com.example.demo.entity.GoodsPhoto;

import java.util.List;

public interface GoodsPhotoService {
    GoodsPhoto save(GoodsPhoto photo);
    List<GoodsPhoto> saveAll(List<GoodsPhoto> photos);
}
