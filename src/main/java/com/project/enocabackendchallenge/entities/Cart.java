package com.project.enocabackendchallenge.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@Data
public class Cart extends BaseEntity {

    private double totalPrice;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

    public Cart() {
    }

    public Cart(Customer customer) {
        this.customer = customer;
    }
}
