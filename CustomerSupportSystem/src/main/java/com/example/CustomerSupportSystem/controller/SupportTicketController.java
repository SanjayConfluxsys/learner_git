package com.example.CustomerSupportSystem.controller;


import com.example.CustomerSupportSystem.DTO.SupportTicketDTO;
import com.example.CustomerSupportSystem.DTO.TicketInfoDTO;
import com.example.CustomerSupportSystem.model.SupportTicket;
import com.example.CustomerSupportSystem.service.SupportTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class SupportTicketController {

    @Autowired
    private SupportTicketService supportTicketService;

    // Create a new support ticket
    @PostMapping("/create")
    public SupportTicket createSupportTicket(@RequestBody SupportTicketDTO supportTicketDTO) {
        return supportTicketService.createSupportTicket(supportTicketDTO);
    }

    // Assign a support ticket to an employee
    @PutMapping("/assign/{ticketId}/{employeeId}")
    public SupportTicket assignTicketToEmployee(@PathVariable Long ticketId, @PathVariable Long employeeId) {
        return supportTicketService.assignTicketToEmployee(ticketId, employeeId);
    }

    // Update the status of a support ticket
    @PutMapping("/updateStatus/{ticketId}")
    public SupportTicket updateTicketStatus(@PathVariable Long ticketId, @RequestParam String status) {
        return supportTicketService.updateTicketStatus(ticketId, status);
    }


    // Endpoint to get all tickets with customer, issue, employee, and status
    @GetMapping("/info")
    public List<TicketInfoDTO> getTicketInfo() {
        return supportTicketService.getTicketInfo();
    }
//
//
//    @PostMapping("/generateTicketSummary")
//    public String generateTicketSummary() {
//        supportTicketService.generateAndSaveTicketSummary();
//        return "Ticket summaries generated and saved successfully!";
//    }

}
