package com.example.demo.entity;

import com.example.demo.entity.dto.UserDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.BeanUtils;

@Entity
@DynamicUpdate
@Table(name = "user")
public class User {

    public User() {}

    public User(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 16, nullable = false)
    private String username;

    @Column(length = 30, nullable = false)
    private String password;

    @NotNull
    @ColumnDefault("0")
    private Boolean isAdmin;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserAddress defaultAddress;

    public static User fromUserDTO(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public UserAddress getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(UserAddress defaultAddress) {
        this.defaultAddress = defaultAddress;
    }
}
