package com.project.enocabackendchallenge.services;

import com.project.enocabackendchallenge.entities.Cart;
import com.project.enocabackendchallenge.entities.CartItem;
import com.project.enocabackendchallenge.entities.Product;
import com.project.enocabackendchallenge.repos.CartItemRepository;
import com.project.enocabackendchallenge.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartService cartService;
    private final ProductService productService;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository, ProductRepository productRepository, CartService cartService, ProductService productService) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository=productRepository;
        this.cartService = cartService;
        this.productService = productService;
    }

    @Transactional
    public CartItem addCartItem(Long cartId,CartItem cartItem) {
        Cart cart=cartService.getCardById(cartId);
        Product product=productService.getProductById(cartItem.getProduct().getId());

        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItemRepository.save(cartItem);

        cart.setTotalPrice(cart.getTotalPrice()+(product.getPrice()*cartItem.getQuantity()));
        cartService.updateCard(cart);

        return cartItem;
    }

    @Transactional
    public CartItem updateCartItem(Long cartItemId,int quantity) {
        CartItem cartItem=cartItemRepository.findById(cartItemId).orElse(null);

        Product product=cartItem.getProduct();
        int currentStock=product.getStockQuantity();
        if(quantity>currentStock)
            throw new RuntimeException("Stokta yeterli ürün yok.");

        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);

        Cart cart=cartItem.getCart();
        cart.setTotalPrice(cart.getTotalPrice()+(product.getPrice()*cartItem.getQuantity()));
        cartService.updateCard(cart);

        return cartItem;
    }

    @Transactional
    public void deleteCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItem getCartItemById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId).orElse(null);
    }
}
