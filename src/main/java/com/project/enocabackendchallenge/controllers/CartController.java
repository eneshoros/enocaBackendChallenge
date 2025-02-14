package com.project.enocabackendchallenge.controllers;

import com.project.enocabackendchallenge.entities.Cart;
import com.project.enocabackendchallenge.requests.AddProductToCartRequest;
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

    @PutMapping("/{cartId}/update-product/{productId}")
    public void updateCart(@PathVariable Long cartId, @PathVariable Long productId, @RequestParam int newQuantity) {
        cartService.updateCart(cartId, productId, newQuantity);
    }

    @PutMapping("/{cartId}/empty")
    public void emptyCart(@PathVariable Long cartId) {
        cartService.emptyCart(cartId);
    }

    @PostMapping("/add-product")
    public void addProductToCart(@RequestBody AddProductToCartRequest request) {
        cartService.addProductToCart(request);
    }

    @DeleteMapping("/{cartId}/remove-product/{productId}")
    public void removeProductFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        cartService.removeProductFromCart(cartId, productId);
    }

}
