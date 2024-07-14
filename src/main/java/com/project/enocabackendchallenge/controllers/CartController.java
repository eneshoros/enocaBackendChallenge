package com.project.enocabackendchallenge.controllers;

import com.project.enocabackendchallenge.entities.Cart;
import com.project.enocabackendchallenge.entities.CartItem;
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
        return cartService.getCardById(id);
    }

    @PutMapping("/update")
    public Cart updateCart(@RequestBody Cart cart) {
        return cartService.updateCard(cart);
    }

    @PostMapping("/add-product")
    public Cart addProductToCart(@RequestBody AddProductToCartRequest newRequest) {
        return cartService.addProductToCart(newRequest);
    }

    @DeleteMapping("/{cartId}/remove-product/{cartItemId}")
    public Cart removeProductFromCart(@PathVariable Long cartId, @PathVariable Long cartItemId) {
        return cartService.removeProductFromCart(cartId, cartItemId);
    }

    @PutMapping("/{cartId}/empty")
    public void emptyCart(@PathVariable Long cartId) {
        cartService.emptyCart(cartId);
    }
}
