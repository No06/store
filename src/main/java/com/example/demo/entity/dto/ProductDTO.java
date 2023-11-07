package com.example.demo.entity.dto;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductCategory;
import com.example.demo.entity.ProductImage;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.List;

public class ProductDTO {
    private Long id;

    private String name;

    private BigDecimal price;

    private BigDecimal discount;

    private Integer stock;

    private String description;

    private ProductCategory category;

    private List<ProductImage> images;

    public static ProductDTO fromProduct(Product product) {
        ProductDTO target = new ProductDTO();
        BeanUtils.copyProperties(product, target);
        return target;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

}
