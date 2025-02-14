package com.project.enocabackendchallenge.services;

import com.project.enocabackendchallenge.entities.Cart;
import com.project.enocabackendchallenge.entities.CartItem;
import com.project.enocabackendchallenge.entities.Product;
import com.project.enocabackendchallenge.repos.CartItemRepository;
import com.project.enocabackendchallenge.repos.CartRepository;
import com.project.enocabackendchallenge.repos.ProductRepository;
import com.project.enocabackendchallenge.requests.AddProductToCartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Transactional
    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }


    @Transactional
    public Cart emptyCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);

        cartItemRepository.deleteByCartId(cartId);

        cart.setTotalPrice(0.0);

        return cartRepository.save(cart);
    }

    public void addProductToCart(AddProductToCartRequest request) {
        Cart cart = cartRepository.findById(request.getCartId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem existingCartItem = cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(request.getProductId()))
                .findFirst()
                .orElse(null);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
            existingCartItem.setPrice(product.getPrice());

            calculateCartTotal(cart);

            cartItemRepository.save(existingCartItem);
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setProduct(product);
            newCartItem.setQuantity(request.getQuantity());
            newCartItem.setPrice(product.getPrice());
            newCartItem.setCart(cart);

            cart.getCartItems().add(newCartItem);

            calculateCartTotal(cart);

            cartItemRepository.save(newCartItem);
        }

        cartRepository.save(cart);
    }

    @Transactional
    public void removeProductFromCart(Long cartId, Long productId) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem cartItemToRemove = cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));

        cart.getCartItems().remove(cartItemToRemove);

        calculateCartTotal(cart);

        cartRepository.save(cart);
    }

    @Transactional
    public void updateCart(Long cartId, Long productId, int newQuantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));


        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));

        if (newQuantity <= 0) {
            cart.getCartItems().remove(cartItem);
            calculateCartTotal(cart);
            cartRepository.save(cart);
            return;
        }

        cartItem.setQuantity(newQuantity);

        cartItem.setPrice(cartItem.getProduct().getPrice());

        calculateCartTotal(cart);

        cartItemRepository.save(cartItem);
        cartRepository.save(cart);
    }

    private void calculateCartTotal(Cart cart) {
        double totalPrice = 0.0;
        for (CartItem item : cart.getCartItems()) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        cart.setTotalPrice(totalPrice);
    }

}
