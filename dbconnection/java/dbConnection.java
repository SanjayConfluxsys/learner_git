//import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
    public static Connection connect(String URL,String USERNAME,String PASSWORD){
            Connection connection = null;
            try{
                connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
                System.out.println("connected to db");
            } catch (SQLException e) {
                System.err.println("connection failed: "+e.getMessage());
            }
            return connection;
    }
}
