package com.project.enocabackendchallenge.services;

import com.project.enocabackendchallenge.entities.Order;
import com.project.enocabackendchallenge.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order placeOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderForCode(String orderCode) {
        return orderRepository.findByOrderCode(orderCode).orElse(null);
    }

    public List<Order> getAllOrdersForCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

}
