package com.example.demo.entity;

import com.example.demo.entity.dto.ProductCategoryDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Entity
@DynamicUpdate
@Table(name = "product_categorys")
@JsonIdentityInfo(generator = ObjectIdGenerators.None.class, property = "id", scope = ProductCategory.class)
public class ProductCategory {
    public ProductCategory() {}
    public ProductCategory(Long id, String name, String description) {
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
    private List<Product> products;

    public static ProductCategory fromDTO(ProductCategoryDTO dto) {
        ProductCategory target = new ProductCategory();
        BeanUtils.copyProperties(dto, target);
        return target;
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
}
