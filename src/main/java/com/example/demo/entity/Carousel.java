package com.example.demo.entity;

import com.example.demo.entity.dto.CarouselDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "carousel")
@DynamicUpdate
public class Carousel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Product product;

    private String title;

    private String description;

    private String subDescription;

    public static Carousel fromCarouselDTO(CarouselDTO carouselDTO) {
        Carousel target = new Carousel();
        BeanUtils.copyProperties(carouselDTO, target);
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
