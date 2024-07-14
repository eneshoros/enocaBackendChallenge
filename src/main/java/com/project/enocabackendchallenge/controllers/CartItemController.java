package com.project.enocabackendchallenge.controllers;

import com.project.enocabackendchallenge.entities.CartItem;
import com.project.enocabackendchallenge.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartItems")
public class CartItemController {

    private final CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        List<CartItem> cartItems = cartItemService.getAllCartItems();
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable("id") Long id) {
        CartItem cartItem = cartItemService.getCartItemById(id);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PostMapping("/{cartId}/add")
    public CartItem addCartItem(@PathVariable Long cartId,@RequestBody CartItem cartItem) {
        return cartItemService.addCartItem(cartId,cartItem);
    }

    @PutMapping("/{cartItemId}/update")
    public CartItem updateCartItem(@PathVariable Long cartItemId, @RequestParam int quantity) {
        return cartItemService.updateCartItem(cartItemId,quantity);
    }

    @DeleteMapping("/{id}")
    public void deleteCartItem(@PathVariable("id") Long id) {
        cartItemService.deleteCartItem(id);
    }
}
