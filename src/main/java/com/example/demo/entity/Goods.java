package com.example.demo.entity;

import com.example.demo.entity.dto.goods.GoodsSaveDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "goods")
@DynamicUpdate
@DynamicInsert
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Goods.class)
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(nullable = false)
    @ColumnDefault("0")
    private BigDecimal price;

    @Column(nullable = false, precision = 3, scale = 2)
    @ColumnDefault("1")
    @PositiveOrZero
    private BigDecimal discount;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer stock;

    @Column(nullable = false)
    @ColumnDefault("''")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private GoodsCategory category;

    @OneToMany(mappedBy = "goods", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("rank asc")
    private List<GoodsPhoto> photos;

    public Goods() {}

    public Goods(GoodsSaveDTO dto) {
        this.id = dto.id;
    }

    public void addPhoto(GoodsPhoto goodsPhoto) {
        photos.add(goodsPhoto);
    }
}
