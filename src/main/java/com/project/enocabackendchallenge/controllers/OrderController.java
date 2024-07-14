package com.project.enocabackendchallenge.controllers;

import com.project.enocabackendchallenge.entities.Order;
import com.project.enocabackendchallenge.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place-order")
    public Order placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getAllOrdersForCustomer(@PathVariable Long customerId) {
        return orderService.getAllOrdersForCustomer(customerId);
    }

    @GetMapping("/order-code/{orderCode}")
    public Order getOrderForCode(@PathVariable String orderCode) {
        return orderService.getOrderForCode(orderCode);
    }
}
