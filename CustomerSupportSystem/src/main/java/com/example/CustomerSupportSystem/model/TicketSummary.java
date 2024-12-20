package com.example.CustomerSupportSystem.model;


import jakarta.persistence.*;

@Entity
public class TicketSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private String customerName;

    @Lob
    @Column(columnDefinition = "json")
    private String ticketSummaryJson;

    private Long createdAt;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTicketSummaryJson() {
        return ticketSummaryJson;
    }

    public void setTicketSummaryJson(String ticketSummaryJson) {
        this.ticketSummaryJson = ticketSummaryJson;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
