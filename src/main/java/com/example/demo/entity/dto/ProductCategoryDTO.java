package com.example.demo.entity.dto;

import com.example.demo.entity.ProductCategory;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class ProductCategoryDTO {
    private Long id;

    private String name;

    private String description;

    private List<ProductDTO> products;

    public static ProductCategoryDTO fromPO(ProductCategory po) {
        ProductCategoryDTO dto = new ProductCategoryDTO();
        BeanUtils.copyProperties(po, dto);
        return dto;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
