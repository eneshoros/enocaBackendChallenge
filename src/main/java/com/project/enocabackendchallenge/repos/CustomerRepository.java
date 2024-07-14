package com.project.enocabackendchallenge.repos;

import com.project.enocabackendchallenge.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
