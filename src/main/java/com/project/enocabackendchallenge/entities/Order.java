package com.project.enocabackendchallenge.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order extends  BaseEntity{
    private String orderCode;
    private double totalPrice;
    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderItem> orderItems=new ArrayList<>();
}
