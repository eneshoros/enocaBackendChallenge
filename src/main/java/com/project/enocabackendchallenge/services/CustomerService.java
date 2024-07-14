package com.project.enocabackendchallenge.services;

import com.project.enocabackendchallenge.entities.Cart;
import com.project.enocabackendchallenge.entities.Customer;
import com.project.enocabackendchallenge.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private CartService cartService;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CartService cartService) {
        this.customerRepository = customerRepository;
        this.cartService = cartService;
    }

    @Transactional
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

}
