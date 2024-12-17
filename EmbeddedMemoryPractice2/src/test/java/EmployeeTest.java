import Connection.EmbeddedConnection;
import EmployeeService.Employee;
import org.neo4j.graphdb.*;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EmployeeTest {
    private GraphDatabaseService graphDb;
    private Employee employee;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeTest.class);

    @BeforeMethod
    public void setUp() {
        EmbeddedConnection.startEmbeddedDatabase();
        graphDb = EmbeddedConnection.getGraphDb();
        employee = new Employee(graphDb);

        try (Transaction tx = graphDb.beginTx()) {
            for (Node node : tx.getAllNodes()) {
                node.delete();
            }
            tx.commit();
        } catch (RuntimeException e) {
            logger.error("Failed to do clean up");
        }
    }

    @Test
    public void testCreateEmployee() {
        employee.createEmployee("Watson", "Developer", "15000");
        // Verify if the employee is created
        try (Transaction tx = graphDb.beginTx()) {
            Node fetchedEmployee = tx.findNode(Label.label("employee"), "name", "Watson");
            Assert.assertNotNull(fetchedEmployee, "employee should be created");
            Assert.assertEquals(fetchedEmployee.getProperty("name"), "Watson", "Name should match");
            Assert.assertEquals(fetchedEmployee.getProperty("role"), "Developer", "Role should match");
            Assert.assertEquals(fetchedEmployee.getProperty("salary"), "15000", "Salary should match");

            // Print the fetched employee details
            System.out.println("Employee details:");
            for (String key : fetchedEmployee.getPropertyKeys()) {
                System.out.println(key + ": " + fetchedEmployee.getProperty(key));
            }
            tx.commit();
        }
    }

    @Test
    public void testGetEmployee() {
        // embeddedEmployeeService.createEmployee("jane","manager","20000");
        employee.getEmployee("jane");
        try (Transaction tx = graphDb.beginTx()) {
            Node node = tx.findNode(Label.label("employee"), "name", "jane");
            Assert.assertNotNull(node, "employee should be found");
            Assert.assertEquals(node.getProperty("name"), "jane", "name should be found");
            for (String key : node.getPropertyKeys()) {
                System.out.println(key + " : " + node.getProperty(key));
            }
            logger.info("employee found {}", node.getAllProperties());
        }
    }

    @Test
    public void testUpdateRole() {
        employee.updateEmployee("Watson", "Developer", "Senior Developer");
        try (Transaction tx = graphDb.beginTx()) {
            Node node = tx.findNode(Label.label("employee"), "name", "Watson");
            Assert.assertNotNull(node, "node should not be null");
            Assert.assertEquals(node.getProperty("newRole"), "Senior Developer");
            for (String key : node.getPropertyKeys()) {
                System.out.println(key + " : " + node.getProperty(key));
            }
        }
    }

    @AfterMethod
    public void tearDown() {
        // Shutdown
        EmbeddedConnection.stopEmbeddedDatabase();
    }
}

