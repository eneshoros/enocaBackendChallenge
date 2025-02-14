package com.project.enocabackendchallenge.controllers;

import com.project.enocabackendchallenge.entities.Order;
import com.project.enocabackendchallenge.requests.OrderItemRequest;
import com.project.enocabackendchallenge.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/placeOrder/{id}")
    public Order placeOrder(@PathVariable("id") Long customerId, @RequestBody List<OrderItemRequest> orderItemRequests) {
        return orderService.placeOrder(customerId, orderItemRequests);
    }

    @GetMapping("/{orderCode}")
    public ResponseEntity<Order> getOrderForCode(@PathVariable String orderCode) {
        try {
            Order order = orderService.getOrderForCode(orderCode);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getAllOrdersForCustomer(@PathVariable Long customerId) {
        try {
            List<Order> orders = orderService.getAllOrdersForCustomer(customerId);

            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
