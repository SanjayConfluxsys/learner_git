import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class CRUDOperationsTest {
    private Connection connection;
    private CRUDOperations crudOperations;

    @BeforeClass
    public void setUp() {
        // Define your connection parameters here
        String url = "url";
        String username = "username";
        String password = "password";

        // Call the connect method and pass the parameters
        connection = dbConnection.connect(url, username, password);
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        crudOperations = new CRUDOperations(connection);
    }

    @Test
    public void testCreateUser() {
        crudOperations.createUser("Ross Gellar", "ross.gellar@example.com");
    }

    @Test(dependsOnMethods = "testCreateUser")
    public void testReadUsers() {
        crudOperations.readUsers();
    }

    @Test(dependsOnMethods = "testReadUsers")
    public void testUpdateUser() {
        crudOperations.updateUser(1,"Ross Gellar","ross.gellar@example.com"); // Use an appropriate ID
    }

    @Test(dependsOnMethods = "testUpdateUser")
    public void testDeleteUser() {
        crudOperations.deleteUser(0); // Use the appropriate ID
    }

    @AfterClass
    public void tearDown() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to close connection: " + e.getMessage());
        }
    }
}
