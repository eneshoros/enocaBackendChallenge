package com.project.enocabackendchallenge.requests;

import lombok.Data;

@Data
public class OrderItemRequest {
    private Long productId;
    private int quantity;
}
