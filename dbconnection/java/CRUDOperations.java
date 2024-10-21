import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUDOperations {
    private Connection connection;

    public CRUDOperations(Connection connection) {
        this.connection = connection;
    }

    // Create
    public void createUser(String name, String email) {
        String query = "INSERT INTO users (name, email) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.executeUpdate();
            System.out.println("User created successfully.");
        } catch (SQLException e) {
            System.err.println("Create operation failed: " + e.getMessage());
        }
    }

    // Read
    public void readUsers() {
        String query = "SELECT * FROM users";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Email: " + rs.getString("email"));
            }
        } catch (SQLException e) {
            System.err.println("Read operation failed: " + e.getMessage());
        }
    }

    // Update
    public void updateUser(int id, String name, String email) {
        String query = "UPDATE users SET name = ?, email = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setInt(3, id);
            stmt.executeUpdate();
            System.out.println("User updated successfully.");
        } catch (SQLException e) {
            System.err.println("Update operation failed: " + e.getMessage());
        }
    }

    // Delete
    public void deleteUser(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("User deleted successfully.");
        } catch (SQLException e) {
            System.err.println("Delete operation failed: " + e.getMessage());
        }
    }
    }
