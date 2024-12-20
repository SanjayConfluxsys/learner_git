package Testing;
import com.example.CustomerSupportSystem.CustomerSupportSystemApplication;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = {CustomerSupportSystemApplication.class})
public class SupportTicketServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SupportTicketService supportTicketService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SupportTicketRepository supportTicketRepository;

    private SupportTicketDTO supportTicketDTO;

    @BeforeMethod
    public void setUp() {
        // Setup SupportTicketDTO with dummy data
        supportTicketDTO = new SupportTicketDTO();
        supportTicketDTO.setDescription("Issue with login");
        supportTicketDTO.setCustomerName("John Doe");
        supportTicketDTO.setCustomerEmail("johndoe@example.com");
        supportTicketDTO.setCustomerPhoneNumber("1234567890");

        // Create an employee for assigning tickets
        Employee employee = new Employee();
        employee.setName("Employee 1");
        employee.setEmail("employee1@example.com");
        employeeRepository.save(employee);
    }

    @Test
    public void testCreateSupportTicket_NewCustomer() {
        // Act: Create the support ticket for a new customer
        SupportTicket ticket = supportTicketService.createSupportTicket(supportTicketDTO);

        // Assert: Verify that the ticket is created and linked to the correct customer and employee
        assertNotNull(ticket, "Ticket should not be null");
        assertEquals(ticket.getDescription(), "Issue with login", "Ticket description should match");
        assertNotNull(ticket.getCustomer(), "Customer should not be null");
        assertEquals(ticket.getCustomer().getEmail(), "johndoe@example.com", "Customer email should match");
        assertNotNull(ticket.getEmployee(), "Employee should be assigned to the ticket");
        assertEquals(ticket.getStatus(), "Assigned", "Ticket status should be 'Assigned'");
    }

    @Test
    public void testCreateSupportTicket_ExistingCustomer() {
        // Arrange: Create an existing customer
        Customer existingCustomer = new Customer();
        existingCustomer.setCustomerName("John Doe");
        existingCustomer.setEmail("johndoe@example.com");
        existingCustomer.setPhoneNumber("1234567890");
        customerRepository.save(existingCustomer);

        // Act: Create the support ticket for the existing customer
        SupportTicket ticket = supportTicketService.createSupportTicket(supportTicketDTO);

        // Assert: Verify that the ticket is created and linked to the correct customer
        assertNotNull(ticket, "Ticket should not be null");
        assertEquals(ticket.getDescription(), "Issue with login", "Ticket description should match");
        assertNotNull(ticket.getCustomer(), "Customer should not be null");
        assertEquals(ticket.getCustomer().getEmail(), "johndoe@example.com", "Customer email should match");
        assertNotNull(ticket.getEmployee(), "Employee should be assigned to the ticket");
        assertEquals(ticket.getStatus(), "Assigned", "Ticket status should be 'Assigned'");
    }

    @Test
    public void testGetTicketInfo() {
        // Act: Create a support ticket to be retrieved
        supportTicketService.createSupportTicket(supportTicketDTO);

        // Act: Fetch ticket info
        List<TicketInfoDTO> ticketInfoList = supportTicketService.getTicketInfo();

        // Assert: Verify that ticket info is fetched correctly
        assertNotNull(ticketInfoList, "Ticket info list should not be null");
        assertTrue(ticketInfoList.size() > 0, "Ticket info list should not be empty");
        assertEquals(ticketInfoList.get(0).getCustomerName(), "John Doe", "Customer name should match");
        assertEquals(ticketInfoList.get(0).getIssue(), "Issue with login", "Issue should match");
        assertNotNull(ticketInfoList.get(0).getEmployeeAssigned(), "Employee assigned should not be null");
        assertEquals(ticketInfoList.get(0).getStatus(), "Assigned", "Ticket status should match");
    }
}

