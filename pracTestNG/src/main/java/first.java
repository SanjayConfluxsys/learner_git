import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class first {
  private Connection connection;
  public first(Connection connection){
      this.connection=connection;
  }
// operations (CRUD)
public void print(int id,String name, String surname) {
   String query = "SELECT * FROM users";
   try{
       Statement stmt = connection.createStatement();
       ResultSet rs = stmt.executeQuery(query);
       while (rs.next()){
           System.out.println("ID: "+rs.getInt("id")+", Name: "+rs.getString("Name")+", Email: "+rs.getString("email"));
       }
   }catch (SQLException e){
       System.err.println("Print operation failed: "+e.getMessage());
   }
}
public void insert(int id, String name, String email){
      String query = "INSERT INTO users (id,name,email) VALUES (?,?,?)";
      try(PreparedStatement stmt = connection.prepareStatement(query))

      {
          stmt.setInt(1, id);
          stmt.setString(2,name);
          stmt.setString(3,email);
          stmt.executeUpdate();
          System.out.println("insert successfull");
      } catch (SQLException e) {
          System.err.println("insert unsuccessfull"+e.getMessage());
      }
}
public void update(int id, String name, String email,int where){
      String query = "UPDATE users SET id = ?, name = ?, email = ? WHERE ID = ?";
      try(PreparedStatement stmt = connection.prepareStatement(query)){
          stmt.setInt(1,id);
          stmt.setString(2,name);
          stmt.setString(3,email);
          stmt.setInt(4, 2);
          stmt.executeUpdate();
          System.out.println("user updated successfully");
      } catch (SQLException e) {
          System.out.println("update operation failed :"+e.getMessage());
      }
}
}


