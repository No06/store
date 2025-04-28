package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "goods_photo")
@DynamicUpdate
@DynamicInsert
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.None.class, property = "id", scope = GoodsPhoto.class)
public class GoodsPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="tinytext")
    private String image_url;

    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer rank;
}
