import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class firstTest {
    private Connection connection;
    private first First;
    @BeforeClass
    public void setup(){
    //define connection
        String URL = "jdbc:postgresql://localhost:5432/db_name";
        String USERNAME = "dummy";
        String PASSWORD = "password";
        //call db method
        connection = dbCon.connect(URL,USERNAME,PASSWORD);
        try{
            connection.setAutoCommit(true);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        First = new first(connection);
    }

    @Test
    public void print(){
        First.print(0,"","");
    }
    @Test
    public void insert(){
        First.insert(0," ","");
    }
    @Test
    public void update(){
        First.update(0," ","",0);
    }

    @AfterClass
    public void teardown(){
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection close");
            }
        } catch(SQLException e){
                System.err.println("Failed to close connection: "+e.getMessage());
        }
    }
}

