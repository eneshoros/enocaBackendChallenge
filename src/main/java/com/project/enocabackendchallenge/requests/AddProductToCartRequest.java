package com.project.enocabackendchallenge.requests;

import lombok.Data;

@Data
public class AddProductToCartRequest {
    Long cartId;
    Long productId;
    int quantity;
}
