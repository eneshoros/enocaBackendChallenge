package com.project.enocabackendchallenge.repos;

import com.project.enocabackendchallenge.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
