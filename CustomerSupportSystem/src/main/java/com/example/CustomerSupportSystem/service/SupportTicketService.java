package com.example.CustomerSupportSystem.service;

import com.example.CustomerSupportSystem.DTO.SupportTicketDTO;
import com.example.CustomerSupportSystem.DTO.TicketInfoDTO;
import com.example.CustomerSupportSystem.model.Customer;
import com.example.CustomerSupportSystem.model.SupportTicket;

import java.util.List;

public interface SupportTicketService {

        SupportTicket createSupportTicket(SupportTicketDTO supportTicketDTO);
        SupportTicket assignTicketToEmployee(Long ticketId, Long employeeId);
        SupportTicket updateTicketStatus(Long ticketId, String status);

        // Method to get the list of ticket details
        List<TicketInfoDTO> getTicketInfo();

        void generateAndSaveTicketSummary(Customer customer, SupportTicket supportTicket);



//    SupportTicket createTicket(Long customerId, String description);
//    List<SupportTicket> getAllTickets();
//    SupportTicket getTicketById(Long id);
//    void assignTicketToEmployee(Long ticketId, Long employeeId);
}
