package com.project.enocabackendchallenge.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "customers")
@Data
public class Customer extends BaseEntity {

    private String name;
    private String email;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Cart cart;

}
