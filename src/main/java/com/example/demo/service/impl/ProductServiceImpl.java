package com.example.demo.service.impl;

import com.example.demo.dao.ProductRepository;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductImage;
import com.example.demo.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    @Override
    public Product findById(int id) throws EntityNotFoundException {
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
    public List<Product> findByPriceRange(double min, double max) {
        return repository.findByPriceRange(min, max);
    }

    @Override
    public Product update(Product product) {
        Product oldProduct = repository.findById(product.getId()).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(product.getId()))
        );
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
}
