package com.project.enocabackendchallenge.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cart_items")
@Data
public class CartItem extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "cart_id",nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    private int quantity;

}
