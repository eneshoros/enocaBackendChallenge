package com.project.enocabackendchallenge.repos;

import com.project.enocabackendchallenge.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteByCartId(Long cartId);

}
