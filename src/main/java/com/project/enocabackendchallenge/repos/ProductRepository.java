package com.project.enocabackendchallenge.repos;

import com.project.enocabackendchallenge.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
