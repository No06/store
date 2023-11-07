package com.example.demo.entity.dto;

import com.example.demo.entity.Carousel;
import com.example.demo.entity.Product;
import org.springframework.beans.BeanUtils;

public class CarouselDTO {
    private Long id;

    private Product product;

    private String title;

    private String description;

    private String subDescription;

    public static CarouselDTO fromCarousel(Carousel carousel) {
        CarouselDTO target = new CarouselDTO();
        BeanUtils.copyProperties(carousel, target);
        return target;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
