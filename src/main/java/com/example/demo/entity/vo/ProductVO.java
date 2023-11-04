package com.example.demo.entity.vo;

import com.example.demo.entity.ProductCategory;
import com.example.demo.entity.ProductImage;
import com.example.demo.entity.dto.ProductDTO;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.List;

public class ProductVO {
    private Integer id;

    private String name;

    private BigDecimal price;

    private BigDecimal discount;

    private Integer stock;

    private String description;

    private ProductCategory category;

    private List<ProductImage> images;

    public static ProductVO fromProductDTO(ProductDTO productDTO) {
        ProductVO productVO = new ProductVO();
        BeanUtils.copyProperties(productDTO, productVO);
        return productVO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
