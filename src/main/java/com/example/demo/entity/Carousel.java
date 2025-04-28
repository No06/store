package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "carousel")
@Data
@DynamicUpdate
public class Carousel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Goods goods;

    private String title;

    private String description;

    private String subDescription;
}
