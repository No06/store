package com.example.demo.service.impl;

import com.example.demo.entity.GoodsCategory;
import com.example.demo.repository.GoodsCategoryRepository;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.service.GoodsCategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {
    private final GoodsCategoryRepository repository;
    private final GoodsRepository goodsRepository;

    @Autowired
    public GoodsCategoryServiceImpl(GoodsCategoryRepository repository,
            GoodsRepository goodsRepository) {
        this.repository = repository;
        this.goodsRepository = goodsRepository;
    }

    @Override
    public List<GoodsCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public GoodsCategory findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    @Override
    public void removeById(Long id) {
        Long count = goodsRepository.countByCategoryId(id);
        if (count != 0) {
            throw new IllegalStateException("请确保要删除的商品类之下没有商品");
        }
        repository.removeById(id);
    }

    @Override
    public void save(GoodsCategory category) {
        repository.save(category);
    }

    @Override
    public Page<GoodsCategory> findPage(Integer page, Integer size) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        return repository.findAll(pageable);
    }
}
