package com.project.enocabackendchallenge.repos;

import com.project.enocabackendchallenge.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByCustomerId(Long customerId);

    Optional<Order> findByOrderCode(String orderCode);
}
