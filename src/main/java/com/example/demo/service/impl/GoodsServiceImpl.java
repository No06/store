package com.example.demo.service.impl;

import com.example.demo.entity.Goods;
import com.example.demo.entity.GoodsCategory;
import com.example.demo.entity.GoodsPhoto;
import com.example.demo.entity.vo.GoodsCategoryWithCountVO;
import com.example.demo.entity.dto.goods.GoodsShowItemDTO;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.service.GoodsService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GoodsServiceImpl implements GoodsService {

    private final GoodsRepository repository;

    @Autowired
    public GoodsServiceImpl(GoodsRepository goodsRepository) {
        this.repository = goodsRepository;
    }

    @Override
    public Optional<Goods> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Goods> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Goods> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Goods> findByCategoryName(String categoryName) {
        return repository.findByCategoryName(categoryName);
    }

    @Override
    public List<Goods> findByNameAndCategoryName(String goodsName, String categoryName) {
        return repository.findByNameAndCategoryName(goodsName, categoryName);
    }

    @Override
    public List<Goods> findByPriceRange(Double min, Double max) {
        return repository.findByPriceRange(min, max);
    }

    @Override
    public Page<Goods> findByNameAndCategoryIdForPage(String name, Long category_id, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Specification<Goods> specification = (root, query, criteriaBuilder) ->  {
            // 创建一个集合，用于存放查询条件
            List<Predicate> predicates = new ArrayList<>();
            if (name != null && !name.isEmpty()) {
                Predicate predicate = criteriaBuilder.like(root.get("name"), "%" + name + "%");
                predicates.add(predicate);
            }
            if (category_id != null) {
                Predicate predicate = criteriaBuilder.equal(root.get("category").get("id"), category_id);
                predicates.add(predicate);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        return repository.findAll(specification, pageable);
    }

    @Override
    public List<GoodsShowItemDTO> findAllItemBySpec(String name, Boolean inStock, Double minPrice, Double maxPrice, Long[] category_id) {
        Specification<Goods> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            // 关键词查找
            if (name != null) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }
            // 仅看有货
            if (inStock != null && inStock) {
                predicates.add(criteriaBuilder.greaterThan(root.get("stock"), 0));
            }
            // 价格筛选
            if (minPrice != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
            }
            if (maxPrice != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
            }
            // 类型筛选
            if (category_id != null && category_id.length > 0) {
                Predicate[] categoryPredicates = new Predicate[category_id.length];
                for (int i = 0; i < category_id.length; i++) {
                    categoryPredicates[i] = criteriaBuilder.equal(root.get("category").get("id"), category_id[i]);
                }
                predicates.add(criteriaBuilder.or(categoryPredicates));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        return repository.findAll(spec).stream().map(GoodsShowItemDTO::new).toList();
    }

    @Override
    public void save(Goods newGoods) {
        if (newGoods.getId() == null) {
            newGoods.getPhotos().forEach((photo) -> photo.setGoods(newGoods));
            repository.save(newGoods);
            return;
        }

        Optional<Goods> optionalGoods = repository.findById(newGoods.getId());
        if (optionalGoods.isEmpty()) {
            throw new RuntimeException("id: " + newGoods.getId() + " goods not found");
        }

        Goods oldGoods = optionalGoods.get();
        if (newGoods.getName() != null) {
            oldGoods.setName(newGoods.getName());
        }
        if (newGoods.getPrice() != null) {
            oldGoods.setPrice(newGoods.getPrice());
        }
        if (newGoods.getDiscount() != null) {
            oldGoods.setDiscount(newGoods.getDiscount());
        }
        if (newGoods.getStock() != null) {
            oldGoods.setStock(newGoods.getStock());
        }
        if (newGoods.getDescription() != null) {
            oldGoods.setDescription(newGoods.getDescription());
        }
        if (newGoods.getCategory() != null) {
            oldGoods.setCategory(newGoods.getCategory());
        }
        if (newGoods.getPhotos() != null) {
            oldGoods.getPhotos().clear();
            for (GoodsPhoto image: newGoods.getPhotos()) {
                oldGoods.addPhoto(image);
            }
        }
        repository.save(oldGoods);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<GoodsCategoryWithCountVO> countByCategory() {
        List<Object[]> result = repository.countAllByCategory();
        return result.stream().map((e) -> new GoodsCategoryWithCountVO((GoodsCategory) e[0], (Long) e[1])).toList();
    }

    @Override
    public Long count() {
        return repository.count();
    }

    @Override
    public Long countByCategoryId(Long categoryId) {
        return repository.countByCategoryId(categoryId);
    }
}
