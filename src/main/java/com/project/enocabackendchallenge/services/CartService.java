package com.project.enocabackendchallenge.services;

import com.project.enocabackendchallenge.entities.Cart;
import com.project.enocabackendchallenge.entities.CartItem;
import com.project.enocabackendchallenge.entities.Customer;
import com.project.enocabackendchallenge.entities.Product;
import com.project.enocabackendchallenge.repos.CartRepository;
import com.project.enocabackendchallenge.repos.CustomerRepository;
import com.project.enocabackendchallenge.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

    public Customer getCustomer(Long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    @Transactional
    public Cart createCart(Long customerId) {
        Cart cart = new Cart();
        Customer customer = getCustomer(customerId);
        cart.setCustomer(customer);

        return cartRepository.save(cart);
    }

    @Transactional
    public void emptyCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);

        cart.getCartItems().clear();
        cart.setTotalPrice(0.0);

        cartRepository.save(cart);
    }

}
