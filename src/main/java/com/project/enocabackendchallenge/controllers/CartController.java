package com.project.enocabackendchallenge.controllers;

import com.project.enocabackendchallenge.entities.Cart;
import com.project.enocabackendchallenge.services.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable Long id) {
        return cartService.getCartById(id);
    }

    @PostMapping("/{customerId}/create-cart")
    public Cart createCartForCustomer(@PathVariable Long customerId) {
        return cartService.createCart(customerId);
    }

    @PutMapping("/{cartId}/empty")
    public void emptyCart(@PathVariable Long cartId) {
        cartService.emptyCart(cartId);
    }
}
