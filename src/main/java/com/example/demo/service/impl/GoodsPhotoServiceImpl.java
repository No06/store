package com.example.demo.service.impl;

import com.example.demo.entity.GoodsPhoto;
import com.example.demo.repository.GoodsPhotoRepository;
import com.example.demo.service.GoodsPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsPhotoServiceImpl implements GoodsPhotoService {
    private final GoodsPhotoRepository repository;

    @Autowired
    public GoodsPhotoServiceImpl(GoodsPhotoRepository repository) {
        this.repository = repository;
    }

    @Override
    public GoodsPhoto save(GoodsPhoto photo) {
        return repository.save(photo);
    }

    public List<GoodsPhoto> saveAll(List<GoodsPhoto> photos) {
        return repository.saveAll(photos);
    }
}
