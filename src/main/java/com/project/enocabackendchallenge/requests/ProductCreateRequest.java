package com.project.enocabackendchallenge.requests;

import lombok.Data;

@Data
public class ProductCreateRequest {
    String name;
    double price;
    int stockQuantity;
}
