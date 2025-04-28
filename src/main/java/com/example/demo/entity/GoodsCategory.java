package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@DynamicUpdate
@Table(name = "goods_categorys")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.None.class, property = "id", scope = GoodsCategory.class)
public class GoodsCategory {
    public GoodsCategory() {}
    public GoodsCategory(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(columnDefinition = "varchar(50) default ''", nullable = false, insertable = false)
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Goods> goodsList;
}
