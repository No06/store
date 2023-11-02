package com.example.demo.entity.dto;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductCategory;
import com.example.demo.entity.ProductImage;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public class ProductItemDTO {
    private String name;

    private BigDecimal price;

    private Integer stock;

    private ProductCategory category;

    private ProductImage image;

    public static ProductItemDTO fromProduct(Product product) {
        ProductItemDTO target = new ProductItemDTO();
        BeanUtils.copyProperties(product, target);
        return target;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public ProductImage getImage() {
        return image;
    }

    public void setImage(ProductImage image) {
        this.image = image;
    }
}
