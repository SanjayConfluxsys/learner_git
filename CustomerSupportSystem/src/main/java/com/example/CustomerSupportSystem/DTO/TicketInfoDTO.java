package com.example.CustomerSupportSystem.DTO;



public class TicketInfoDTO {

    private String customerName;
    private String issue;
    private String employeeAssigned;
    private String status;

    // Constructor
    public TicketInfoDTO(String customerName, String issue, String employeeAssigned, String status) {
        this.customerName = customerName;
        this.issue = issue;
        this.employeeAssigned = employeeAssigned;
        this.status = status;
    }

    // Getters and Setters
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getEmployeeAssigned() {
        return employeeAssigned;
    }

    public void setEmployeeAssigned(String employeeAssigned) {
        this.employeeAssigned = employeeAssigned;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
