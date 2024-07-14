package com.project.enocabackendchallenge.controllers;

import com.project.enocabackendchallenge.entities.Cart;
import com.project.enocabackendchallenge.entities.CartItem;
import com.project.enocabackendchallenge.requests.AddProductToCartRequest;
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

    @GetMapping("/all")
    public List<CartItem> getAllCartItems() {
        List<CartItem> cartItems = cartItemService.getAllCartItems();
        return cartItems;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable("id") Long id) {
        CartItem cartItem = cartItemService.getCartItemById(id);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PostMapping("/add")
    public CartItem AddProductToCart(@RequestBody AddProductToCartRequest newRequest) {
        return cartItemService.addProductToCart(newRequest);
    }

    @PutMapping("/update/{id}")
    public CartItem updateCartItem(@PathVariable("id") Long id, @RequestBody AddProductToCartRequest newRequest) {
        return cartItemService.updateCartItem(id, newRequest);
    }

    @PutMapping("/{cartItemId}/empty")
    public void emptyCart(@PathVariable Long cartItemId) {
        cartItemService.emptyCartItem(cartItemId);
    }

    @DeleteMapping("/{cartId}/remove-product/{cartItemId}")
    public Cart removeProductFromCart(@PathVariable Long cartId, @PathVariable Long cartItemId) {
        return cartItemService.removeProductFromCart(cartId, cartItemId);
    }

    @DeleteMapping("/{id}")
    public void deleteCartItem(@PathVariable("id") Long id) {
        cartItemService.deleteCartItem(id);
    }
}
