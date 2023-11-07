package com.example.demo.entity.vo;

import com.example.demo.entity.ProductCategory;
import com.example.demo.entity.ProductImage;
import com.example.demo.entity.dto.ProductDTO;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public class ProductItemVO {
    private Long id;

    private String name;

    private BigDecimal price;

    private BigDecimal discount;

    private Integer stock;

    private ProductCategory category;

    private ProductImage image;

    public static ProductItemVO fromProductDTO(ProductDTO productDTO) {
        ProductItemVO productItemVO = new ProductItemVO();
        BeanUtils.copyProperties(productDTO, productItemVO);
        try {
            productItemVO.setImage(productDTO.getImages().get(0));
        } catch (IndexOutOfBoundsException e) {
            productItemVO.setImage(null);
        }
        return productItemVO;
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
