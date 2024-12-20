package com.example.CustomerSupportSystem.repository;

import com.example.CustomerSupportSystem.model.TicketSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketSummaryRepository extends JpaRepository<TicketSummary, Long> {
}
