package com.example.demo.entity;

import com.example.demo.entity.dto.carousel.CarouselSaveDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "carousel")
@Data
@DynamicUpdate
@DynamicInsert
public class Carousel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Goods goods;

    @NotNull
    private String title;

    private String description;

    private String subDescription;

    public Carousel(CarouselSaveDTO dto) {
        this.id = dto.id;
        this.goods = new Goods();
        goods.setId(dto.goodsId);
        this.title = dto.title;
        this.description = dto.description;
        this.subDescription = dto.subDescription;
    }

    public Carousel() {

    }
}
