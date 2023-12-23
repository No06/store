package com.example.demo.entity;

import com.example.demo.entity.dto.UserAddressDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.BeanUtils;

// 用户地址类
@Entity
@DynamicUpdate
@Table(name = "user_address")
public class UserAddress {
    public static Integer MAX_COUNT = 10; // 用户收货地址存储上限

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 收货人姓名
    @NotNull
    @Column(length = 50)
    private String name;

    // 收货人电话
    @NotNull
    @Column(length = 20)
    private String phone;

    // 收货人地址
    @NotNull
    @Column(length = 200)
    private String address;

    public static UserAddress fromDTO(UserAddressDTO dto) {
        UserAddress target = new UserAddress();
        BeanUtils.copyProperties(dto, target);
        return target;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
