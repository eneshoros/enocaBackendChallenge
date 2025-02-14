package com.project.enocabackendchallenge.services;

import com.project.enocabackendchallenge.entities.Order;
import com.project.enocabackendchallenge.entities.OrderItem;
import com.project.enocabackendchallenge.entities.Product;
import com.project.enocabackendchallenge.repos.CustomerRepository;
import com.project.enocabackendchallenge.repos.OrderRepository;
import com.project.enocabackendchallenge.repos.ProductRepository;
import com.project.enocabackendchallenge.requests.OrderItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,ProductRepository productRepository,CustomerRepository customerRepository){
        this.orderRepository=orderRepository;
        this.productRepository=productRepository;
        this.customerRepository=customerRepository;
    }

    @Transactional
    public Order placeOrder(Long customerId, List<OrderItemRequest> orderItemRequests) {
        double totalPrice = 0;
        UUID uuid=UUID.randomUUID();
        Order order = new Order();
        order.setCustomer(customerRepository.findById(customerId).orElse(null)); // Müşteri ID'ye göre müşteri bilgisi alınır.

        if(order.getOrderItems()==null){
            order.setOrderItems((new ArrayList<>()));
        }

        for (OrderItemRequest itemRequest : orderItemRequests) {
            Product product = productRepository.findById(itemRequest.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
            if (!product.isInStock()) {
                throw new RuntimeException("Product " + product.getName() + " is out of stock.");
            }

            product.reduceStock(itemRequest.getQuantity());
            double itemPrice = product.getPrice() * itemRequest.getQuantity();
            totalPrice += itemPrice;

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPriceAtOrderTime(product.getPrice());
            orderItem.setOrder(order);

            order.getOrderItems().add(orderItem);
        }

        order.setStatus("Order Received.");
        order.setOrderCode("ORD"+uuid.toString());
        order.setTotalPrice(totalPrice);

        return orderRepository.save(order);
    }

    public Order getOrderForCode(String orderCode) {
        return orderRepository.findByOrderCode(orderCode).orElseThrow(() -> new RuntimeException("Order not found for code: " + orderCode));
    }

    public List<Order> getAllOrdersForCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

}
