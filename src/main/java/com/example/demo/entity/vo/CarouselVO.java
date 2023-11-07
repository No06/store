package com.example.demo.entity.vo;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductImage;
import com.example.demo.entity.dto.CarouselDTO;
import org.springframework.beans.BeanUtils;

public class CarouselVO {
    private Long id;

    private Long product_id;

    private String title;

    private ProductImage image;

    private String description;

    private String subDescription;

    public static CarouselVO fromCarouselDTO(CarouselDTO dto) {
        CarouselVO target = new CarouselVO();
        Product dtoProduct = dto.getProduct();
        target.setProduct_id(dtoProduct.getId());
        target.setImage(dtoProduct.getImages().get(0));
        BeanUtils.copyProperties(dto, target);
        if (target.getTitle() == null) {
            target.setTitle(dtoProduct.getName());
        }
        return target;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ProductImage getImage() {
        return image;
    }

    public void setImage(ProductImage image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubDescription() {
        return subDescription;
    }

    public void setSubDescription(String subDescription) {
        this.subDescription = subDescription;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }
}
