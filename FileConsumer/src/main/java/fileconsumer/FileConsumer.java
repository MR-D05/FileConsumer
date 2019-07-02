package fileconsumer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Timestamp;

public class FileConsumer {

    //This program parses a file and inserts valid data into an in-memory database.
    public static void main(String[] args) {
        try {
            //Load driver
            Class.forName("org.sqlite.JDBC");

            //Statistical fields.
            int numberOfEntries = 0, goodEntries = 0, badEntries = 0;
            String line;
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            Connection connection;
            Statement statement;
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            System.out.println("Connection to database successful.");

            //Create table if it does not already exist with requisite columns.
            statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS test (A VARCHAR, B VARCHAR, C VARCHAR, D VARCHAR, E VARCHAR, F VARCHAR, G VARCHAR, H VARCHAR, I VARCHAR, J VARCHAR)";
            statement.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
