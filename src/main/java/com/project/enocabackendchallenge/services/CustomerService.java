package com.project.enocabackendchallenge.services;

import com.project.enocabackendchallenge.entities.Cart;
import com.project.enocabackendchallenge.entities.Customer;
import com.project.enocabackendchallenge.repos.CartRepository;
import com.project.enocabackendchallenge.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CartService cartService;
    private final CartRepository cartRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CartService cartService,CartRepository cartRepository) {
        this.cartRepository=cartRepository;
        this.customerRepository = customerRepository;
        this.cartService = cartService;
    }

    @Transactional
    public Cart createCart(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        Cart cart = new Cart();
        cart.setCustomer(customer);

        return cartRepository.save(cart);
    }

    @Transactional
    public Customer addCustomer(Customer customer) {
        Customer savedCustomer=customerRepository.save(customer);

        createCart(savedCustomer.getId());

        return savedCustomer;
    }

}
