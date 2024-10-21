import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {
    private static final String URL = "database url";
    private static final String USER = "userName";
    private static final String PASSWORD = "password";

    public static Connection connect() {
        Connection connection = null; try {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);

        System.out.println("Connected to the database.");
        } catch (SQLException e) {

        System.err.println("Connection failed: " + e.getMessage());
    }
        return connection;
    }
}
