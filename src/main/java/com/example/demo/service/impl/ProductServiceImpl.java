package com.example.demo.service.impl;

import com.example.demo.entity.ProductCategory;
import com.example.demo.repository.ProductRepository;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductImage;
import com.example.demo.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    @Override
    public Product findById(Integer id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("商品ID:"+ id +"不存在"));
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Product> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Product> findByCategoryName(String categoryName) {
        return repository.findByCategoryName(categoryName);
    }

    @Override
    public List<Product> findByNameAndCategoryName(String productName, String categoryName) {
        return repository.findByNameAndCategoryName(productName, categoryName);
    }

    @Override
    public List<Product> findByPriceRange(Double min, Double max) {
        return repository.findByPriceRange(min, max);
    }

    @Override
    public List<Product> findAllBySpec(boolean inStock, Double minPrice, Double maxPrice, Integer[] category_id) {
        Specification<Product> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            // 仅看有货
            if (inStock) {
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
        return repository.findAll(spec);
    }

    @Override
    public Product save(Product product) {
        Product oldProduct;
        try {
            oldProduct = repository.findById(product.getId()).orElseThrow(
                    () -> new EntityNotFoundException(String.valueOf(product.getId()))
            );
        } catch (Exception e) {
            product.getImages().forEach((image) -> image.setProduct(product));
            return repository.save(product);
        }
        if (product.getName() != null) {
            oldProduct.setName(product.getName());
        }
        if (product.getPrice() != null) {
            oldProduct.setPrice(product.getPrice());
        }
        if (product.getDiscount() != null) {
            oldProduct.setDiscount(product.getDiscount());
        }
        if (product.getStock() != null) {
            oldProduct.setStock(product.getStock());
        }
        if (product.getDescription() != null) {
            oldProduct.setDescription(product.getDescription());
        }
        if (product.getCategory() != null) {
            oldProduct.setCategory(product.getCategory());
        }
        if (product.getImages() != null) {
            oldProduct.getImages().clear();
            for (ProductImage image: product.getImages()) {
                oldProduct.addImage(image);
            }
        }
        return repository.save(oldProduct);
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Object[]> countProductsByCategory() {
        return repository.countProductsByCategory();
    }
}
