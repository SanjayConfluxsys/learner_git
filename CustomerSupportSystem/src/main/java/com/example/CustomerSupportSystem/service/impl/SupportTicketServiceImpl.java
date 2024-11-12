package com.example.CustomerSupportSystem.service.impl;

import com.example.CustomerSupportSystem.DTO.SupportTicketDTO;
import com.example.CustomerSupportSystem.DTO.TicketInfoDTO;
import com.example.CustomerSupportSystem.model.Customer;
import com.example.CustomerSupportSystem.model.Employee;
import com.example.CustomerSupportSystem.model.SupportTicket;
import com.example.CustomerSupportSystem.repository.CustomerRepository;
import com.example.CustomerSupportSystem.repository.EmployeeRepository;
import com.example.CustomerSupportSystem.repository.SupportTicketRepository;
import com.example.CustomerSupportSystem.service.SupportTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class SupportTicketServiceImpl implements SupportTicketService {

    @Autowired
    private SupportTicketRepository supportTicketRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public SupportTicket createSupportTicket(SupportTicketDTO supportTicketDTO) {
        // First check if the customer already exists by email
        Optional<Customer> existingCustomer = customerRepository.findByEmail(supportTicketDTO.getCustomerEmail());

        Customer customer;

        if (existingCustomer.isPresent()) {
            // If customer exists, use the existing customer
            customer = existingCustomer.get();
        } else {
            // If customer does not exist, create a new customer
            customer = new Customer();
            customer.setCustomerName(supportTicketDTO.getCustomerName());
            customer.setEmail(supportTicketDTO.getCustomerEmail());
            customer.setPhoneNumber(supportTicketDTO.getCustomerPhoneNumber());
            customer = customerRepository.save(customer);  // Save the new customer
        }

        // Fetch all employees from the database
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            throw new RuntimeException("No employees available to assign the ticket");
        }

        // Select a random employee from the list of employees
        Random random = new Random();
        Employee randomEmployee = employees.get(random.nextInt(employees.size()));

        // Create and save the new support ticket
        SupportTicket ticket = new SupportTicket();
        ticket.setDescription(supportTicketDTO.getDescription());
        ticket.setStatus("Assigned"); // Set status as "Assigned" since we are assigning an employee immediately
        ticket.setCustomer(customer);  // Associate the ticket with the customer
        ticket.setEmployee(randomEmployee);  // Assign the random employee

        // Save the ticket to the database
        return supportTicketRepository.save(ticket);
    }

    @Override
    @Transactional
    public SupportTicket assignTicketToEmployee(Long ticketId, Long employeeId) {
        SupportTicket ticket = supportTicketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Support ticket not found"));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Assign the ticket to the employee
        ticket.setEmployee(employee);
        ticket.setStatus("Assigned");

        return supportTicketRepository.save(ticket);
    }

    @Override
    @Transactional
    public SupportTicket updateTicketStatus(Long ticketId, String status) {
        SupportTicket ticket = supportTicketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Support ticket not found"));

        // Update the status of the ticket
        ticket.setStatus(status);

        return supportTicketRepository.save(ticket);
    }


    @Override
    public List<TicketInfoDTO> getTicketInfo() {
        return supportTicketRepository.getTicketInfo();
    }


}













//
//import com.example.CustomerSupportSystem.model.Customer;
//import com.example.CustomerSupportSystem.model.Employee;
//import com.example.CustomerSupportSystem.model.SupportTicket;
//import com.example.CustomerSupportSystem.repository.CustomerRepository;
//import com.example.CustomerSupportSystem.repository.EmployeeRepository;
//import com.example.CustomerSupportSystem.repository.SupportTicketRepository;
//import com.example.CustomerSupportSystem.service.SupportTicketService;
//import org.apache.velocity.exception.ResourceNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Random;
//
//public class SupportTicketServiceImpl implements SupportTicketService {
//    private final CustomerRepository customerRepository;
//    private final EmployeeRepository employeeRepository;
//    private final SupportTicketRepository supportTicketRepository;
//
//    @Autowired
//    public SupportTicketServiceImpl(CustomerRepository customerRepository,
//                                    EmployeeRepository employeeRepository,
//                                    SupportTicketRepository supportTicketRepository) {
//        this.customerRepository = customerRepository;
//        this.employeeRepository = employeeRepository;
//        this.supportTicketRepository = supportTicketRepository;
//    }
//
//    @Override
//    public SupportTicket createTicket(Long customerId, String description) {
//        // Find the customer who is submitting the ticket
//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
//
//        // Create the support ticket
//        SupportTicket ticket = new SupportTicket();
//        ticket.setDescription(description);
//        ticket.setStatus("open"); // Default status is open
//        ticket.setCustomer(customer);
//        ticket.setCreatedAt(LocalDateTime.now());
//
//        // Randomly assign an employee to the ticket
//        List<Employee> employees = employeeRepository.findAll();
//        if (employees.isEmpty()) {
//            throw new RuntimeException("No employees available to assign the ticket");
//        }
//        Random random = new Random();
//        Employee assignedEmployee = employees.get(random.nextInt(employees.size()));
//        ticket.setEmployee(assignedEmployee);
//
//        // Save and return the ticket
//        return supportTicketRepository.save(ticket);
//    }
//
//    @Override
//    public List<SupportTicket> getAllTickets() {
//        return supportTicketRepository.findAll();
//    }
//
//    @Override
//    public SupportTicket getTicketById(Long id) {
//        return supportTicketRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));
//    }
//
//    @Override
//    public void assignTicketToEmployee(Long ticketId, Long employeeId) {
//        // Fetch the ticket and employee
//        SupportTicket ticket = supportTicketRepository.findById(ticketId)
//                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
//
//        // Assign the employee to the ticket
//        ticket.setEmployee(employee);
//
//        // Save the updated ticket
//        supportTicketRepository.save(ticket);
//    }
//}
