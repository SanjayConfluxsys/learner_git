import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class CSVWrite {
    public static void main(String[] args) {
        Connection connection = databaseConnection.connect();
        String csvFile = "Specify_path.csv"; // Specify your output CSV file path
        try (FileWriter writer = new FileWriter(csvFile);
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Account Id","Account Login","Status","$metaType","Groups.Group"))) {
            if (connection != null) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select as2.Account_id as \"Account Id\",as2.Account_login as \"Account Login\" ,as2.Status as \"Status\" , as2.meta_type as \"$metaType\", gs.entitlement as \"Groups.Group\"\n" +
                        "from account_sanjay as2  left join group_sanjay gs on gs.Account_id = as2.Account_id;");
                while (resultSet.next()) {
                    String column1 = resultSet.getString("Account id");
                    String column2 = resultSet.getString("Account Login");
                    String column3 = resultSet.getString("Status");
                    String column4 = resultSet.getString("$metaType");
                    String column5 = resultSet.getString("Groups.Group");
                    csvPrinter.printRecord(column1, column2, column3,column4,column5);
                }
                System.out.println("CSV file created successfully!");
            }
        }
        catch (Exception e) { e.printStackTrace();
        }
        finally {
            try {
            if (connection != null) connection.close(); } catch (SQLException e) {
            e.printStackTrace();
            }
        }
    }
}
