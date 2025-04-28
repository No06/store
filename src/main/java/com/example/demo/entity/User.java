package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.example.demo.entity.dto.user.UserRegisterDTO;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "user")
@Data
public class User {
    public User() {}

    public User(Long id) {
        this.id = id;
    }

    public User(UserRegisterDTO dto) {
        this.username = dto.getUsername();
        this.password = dto.getPassword();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 16, nullable = false)
    private String username;

    @Column(length = 30, nullable = false)
    private String password;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Boolean isAdmin;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserAddress defaultAddress;
}
