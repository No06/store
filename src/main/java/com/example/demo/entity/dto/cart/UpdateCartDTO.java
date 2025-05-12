package com.example.demo.entity.dto.cart;

public class UpdateCartDTO {
    public final Long id;
    public final Integer quantity;
    public final Boolean isSelected;

    public UpdateCartDTO(Long id, Integer quantity, Boolean isSelected) {
        this.id = id;
        this.quantity = quantity;
        this.isSelected = isSelected;
    }
}
