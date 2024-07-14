package com.project.enocabackendchallenge.repos;

import com.project.enocabackendchallenge.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
}
