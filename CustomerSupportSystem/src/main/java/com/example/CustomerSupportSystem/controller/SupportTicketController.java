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

}













//
//import com.example.CustomerSupportSystem.DTO.TicketRequest;
//import com.example.CustomerSupportSystem.model.SupportTicket;
//import com.example.CustomerSupportSystem.service.SupportTicketServicedummy;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//@RestController
//@RequestMapping("/api/support-tickets")
//public class SupportTicketController {
//
//    @Autowired
//    private SupportTicketServicedummy supportTicketService;
//
//    // Endpoint to create a new ticket
//    @PostMapping("/create")
//    public ResponseEntity<SupportTicket> createTicket(@RequestBody TicketRequest ticketRequest) {
//        SupportTicket ticket = supportTicketService.createTicket(ticketRequest.getCutomerId(), ticketRequest.getDescription());
//        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
//    }
//
////    @PostMapping("/create")
////    public ResponseEntity<SupportTicket> createTicket(@RequestBody TicketRequest ticketRequest){
////        SupportTicket ticket = supportTicketService.createTicket(ticketRequest.getCutomerId(),ticketRequest.getDescription());
////        return new ResponseEntity<>(ticket,HttpStatus.CREATED);
////    }
//
//
//
//
//    // Endpoint to get all tickets for a specific employee
//    @GetMapping("/employee/{employeeId}")
//    public ResponseEntity<List<SupportTicket>> getTicketsForEmployee(@PathVariable Long employeeId) {
//        List<SupportTicket> tickets = supportTicketService.getTicketsForEmployee(employeeId);
//        return new ResponseEntity<>(tickets, HttpStatus.OK);
//    }
//
//    // Endpoint to get all tickets for a specific customer
//    @GetMapping("/customer/{customerId}")
//    public ResponseEntity<List<SupportTicket>> getTicketsForCustomer(@PathVariable Long customerId) {
//        List<SupportTicket> tickets = supportTicketService.getTicketsForCustomer(customerId);
//        return new ResponseEntity<>(tickets, HttpStatus.OK);
//    }
//}

//
//import com.example.CustomerSupportSystem.model.SupportTicket;
//import com.example.CustomerSupportSystem.service.SupportTicketService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/tickets")
//public class SupportTicketController {
//
//    private final SupportTicketService supportTicketService;
//
//    @Autowired
//    public SupportTicketController(SupportTicketService supportTicketService) {
//        this.supportTicketService = supportTicketService;
//    }
//
//    @PostMapping
//    public ResponseEntity<SupportTicket> createTicket(@RequestParam Long customerId,
//                                                      @RequestParam String description) {
//        SupportTicket ticket = supportTicketService.createTicket(customerId, description);
//        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<SupportTicket>> getAllTickets() {
//        List<SupportTicket> tickets = supportTicketService.getAllTickets();
//        return new ResponseEntity<>(tickets, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<SupportTicket> getTicketById(@PathVariable Long id) {
//        SupportTicket ticket = supportTicketService.getTicketById(id);
//        return new ResponseEntity<>(ticket, HttpStatus.OK);
//    }
//
//    @PostMapping("/{ticketId}/assign")
//    public ResponseEntity<Void> assignTicketToEmployee(@PathVariable Long ticketId,
//                                                       @RequestParam Long employeeId) {
//        supportTicketService.assignTicketToEmployee(ticketId, employeeId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//}
