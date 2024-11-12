package com.example.CustomerSupportSystem.repository;

import com.example.CustomerSupportSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
