package com.example.CustomerSupportSystem.repository;

import com.example.CustomerSupportSystem.DTO.TicketInfoDTO;
import com.example.CustomerSupportSystem.model.Employee;
import com.example.CustomerSupportSystem.model.SupportTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupportTicketRepository extends JpaRepository<SupportTicket, Long> {
    List<SupportTicket> findByEmployee(Employee employee);


    // Query to join customer, support_ticket, and employee and return ticket details
    @Query("SELECT new com.example.CustomerSupportSystem.DTO.TicketInfoDTO(c.customerName, st.description, e.name, st.status) " +
            "FROM SupportTicket st " +
            "JOIN st.customer c " +
            "JOIN st.employee e")
    List<TicketInfoDTO> getTicketInfo();

//
//
//    @Query("SELECT new com.example.CustomerSupportSystem.DTO.TicketInfoDTO(c.customerName, st.description, e.name, st.status) " +
//            "FROM SupportTicket st " +
//            "JOIN st.customer c " +
//            "JOIN st.employee e")
//    List<TicketInfoDTO> getTicketInfojson();
}
