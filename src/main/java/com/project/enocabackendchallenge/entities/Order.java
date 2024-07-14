package com.project.enocabackendchallenge.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order extends BaseEntity {

    @Column(unique = true)
    private String orderCode;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    private LocalDateTime orderDate;

}
