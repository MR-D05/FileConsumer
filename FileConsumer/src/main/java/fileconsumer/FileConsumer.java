package fileconsumer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Scanner;

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
            
            //Use Scanner to isolate file
            Scanner scan = new Scanner(System.in);
            System.out.print("Filename: ");
            String filename = scan.next();
            scan.close();

            File file = null;
            if (!filename.isEmpty())
                file = new File(filename);
            else {
                System.err.println("Unknown filename.");
                System.exit(0);
            }

            //Use FileReader and BufferedReader to parse file.
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            PrintWriter writer = new PrintWriter("bad-data-" + timestamp.getTime() + ".csv", "UTF-8");

            //Initialize bad-data-<timestamp>.csv file with appropriate header from input file.
            line = br.readLine();
            writer.println(line);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
