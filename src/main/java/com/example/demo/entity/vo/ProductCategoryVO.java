package com.example.demo.entity.vo;

import com.example.demo.entity.dto.ProductCategoryDTO;
import org.springframework.beans.BeanUtils;

public class ProductCategoryVO {
    private Integer id;

    private String name;

    private String description;

    public static ProductCategoryVO fromDTO(ProductCategoryDTO dto) {
        ProductCategoryVO vo = new ProductCategoryVO();
        BeanUtils.copyProperties(dto, vo);
        return vo;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
