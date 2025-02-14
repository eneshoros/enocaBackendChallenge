package com.project.enocabackendchallenge.controllers;

import com.project.enocabackendchallenge.entities.Customer;
import com.project.enocabackendchallenge.services.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

}
