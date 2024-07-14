package com.project.enocabackendchallenge.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "orderDetail")
@Data
public class OrderDetail extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;

    @Column(name = "product_id",nullable = false)
    private Long productId;

    @Column(nullable = false)
    private int quantity;

    private double priceAtOrder;

}
