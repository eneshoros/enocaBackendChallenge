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

import java.util.List;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartService cartService;
    private final ProductService productService;
    private final CartRepository cartRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository, ProductRepository productRepository,
                           CartService cartService, ProductService productService, CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.cartService = cartService;
        this.productService = productService;
        this.cartRepository = cartRepository;
    }

    @Transactional
    public CartItem addProductToCart(AddProductToCartRequest request) {
        Cart cart = cartService.getCartById(request.getCartId());
        Product product = productService.getProductById(request.getProductId());

        if (product.getStockQuantity() < request.getQuantity()) {
            throw new RuntimeException("Üzgünüz, stokta yeterli ürün yok.");
        }

        boolean productExistsInCart = false;
        for (CartItem item : cart.getCartItems()) {
            if (item.getProductId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + request.getQuantity());
                item.setPrice(item.getPrice() + product.getPrice());
                productExistsInCart = true;
                break;
            }
        }

        if (!productExistsInCart) {
            CartItem newCartItem = new CartItem();
            newCartItem.setProductId(product.getId());
            newCartItem.setQuantity(request.getQuantity());
            newCartItem.setPrice(product.getPrice());
            newCartItem.setCart(cart);
            cart.getCartItems().add(newCartItem);

            calculateCartTotal(cart);

            cartItemRepository.save(newCartItem);

            return newCartItem;
        }
        return null;
    }

    private void calculateCartTotal(Cart cart) {
        double totalPrice = 0.0;
        for (CartItem item : cart.getCartItems()) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        cart.setTotalPrice(totalPrice);
    }

    @Transactional
    public Cart removeProductFromCart(Long cartId, Long cartItemId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        boolean isRemove = cart.getCartItems().removeIf(item -> item.getId().equals(cartItemId));
        if (isRemove) {
            deleteCartItem(cartItemId);
        }
        calculateCartTotal(cart);

        return cartRepository.save(cart);

    }

    @Transactional
    public CartItem updateCartItem(Long cartItemId, AddProductToCartRequest request) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);

        if (request.getQuantity() <= 0) {
            throw new RuntimeException("Miktar 0 veya daha az olamaz");
        }

        cartItem.setQuantity(request.getQuantity());
        cartItemRepository.save(cartItem);

        Cart cart = cartItem.getCart();
        calculateCartTotal(cart);

        return cartItem;
    }

    @Transactional
    public void emptyCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);

        cartItem.setQuantity(0);
        cartItem.setPrice(0.0);

        cartItemRepository.save(cartItem);
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
