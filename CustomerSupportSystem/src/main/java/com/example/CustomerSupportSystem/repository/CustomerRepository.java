package com.example.CustomerSupportSystem.repository;

import com.example.CustomerSupportSystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);  // Method to find customer by email
}
