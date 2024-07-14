package com.project.enocabackendchallenge.services;

import com.project.enocabackendchallenge.entities.Cart;
import com.project.enocabackendchallenge.entities.CartItem;
import com.project.enocabackendchallenge.entities.Product;
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

    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Cart getCardById(Long cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

    @Transactional
    public Cart updateCard(Cart cart) {
        return cartRepository.save(cart);
    }

    @Transactional
    public void emptyCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);

        cart.getCartItems().clear();
        cart.setTotalPrice(0.0);

        cartRepository.save(cart);
    }

    @Transactional
    public Cart addProductToCart(AddProductToCartRequest newRequest) {
        Cart cart = cartRepository.findById(newRequest.getCartId()).orElse(null);
        Product product = productRepository.findById(newRequest.getProductId()).orElse(null);

        if (product.getStockQuantity() < newRequest.getQuantity()) {
            throw new RuntimeException("Ürün stoku yeterli değil");
        }

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(newRequest.getQuantity());

        cart.getCartItems().add(cartItem);

        calculateAndUpdateCartTotalPrice(cart);

        cartRepository.save(cart);

        product.setStockQuantity(product.getStockQuantity() - newRequest.getQuantity());

        productRepository.save(product);

        return cart;
    }

    @Transactional
    public Cart removeProductFromCart(Long cartId, Long cartItemId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        cart.getCartItems().removeIf(item->item.getId().equals(cartItemId));

        calculateAndUpdateCartTotalPrice(cart);

        return cartRepository.save(cart);

    }

    private void calculateAndUpdateCartTotalPrice(Cart cart) {
        double totalPrice = cart.getCartItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        cart.setTotalPrice(totalPrice);
    }

}
