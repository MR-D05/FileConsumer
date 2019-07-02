package fileconsumer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Scanner;

public class FileConsumer {

	//Program to parse a file and insert valid data into a database
	public static void main(String[] args) {
		try {
			//Load driver
			Class.forName("org.sqlite.JDBC");

			//Statistical fields
			int numberOfEntries = 0, goodEntries = 0, badEntries = 0;
			String line;
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());

			Connection connection;
			Statement statement;
			connection = DriverManager.getConnection("jdbc:sqlite:test.db");
			System.out.println("Connection to database successful.");

			//Create table if it does not already exist with requisite columns
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

			//Use FileReader and BufferedReader to parse file
			FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader(fileReader);
			PrintWriter writer = new PrintWriter("bad-data-" + timestamp.getTime() + ".csv", "UTF-8");

			//Initialize bad-data-<timestamp>.csv file with appropriate header from input file
			line = br.readLine();
			writer.println(line);

			//Go over file
			for (line = br.readLine(); line != null; line = br.readLine()) {
				//Break up each line by comma and store result in string array
				String[] currentLine = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
				//Use helper function to determine bad entries
				if (anyIndexIsEmpty(currentLine) == true) {
					writer.println(line);
					badEntries++;
				}
				//Good entries are added to the database
				else {
					sql = "INSERT INTO test (A, B, C, D, E, F, G, H, I, J) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					statement = connection.prepareStatement(sql);
					((PreparedStatement) statement).setString(1, currentLine[0]);
					((PreparedStatement) statement).setString(2, currentLine[1]);
					((PreparedStatement) statement).setString(3, currentLine[2]);
					((PreparedStatement) statement).setString(4, currentLine[3]);
					((PreparedStatement) statement).setString(5, currentLine[4]);
					((PreparedStatement) statement).setString(6, currentLine[5]);
					((PreparedStatement) statement).setString(7, currentLine[6]);
					((PreparedStatement) statement).setString(8, currentLine[7]);
					((PreparedStatement) statement).setString(9, currentLine[8]);
					((PreparedStatement) statement).setString(10, currentLine[9]);
					((PreparedStatement) statement).executeUpdate();
					goodEntries++;
				}
				numberOfEntries++;
			}
			br.close();
			writer.close();
			statement.close();
			connection.close();

			System.out.println("Number of entries: " + numberOfEntries);
			System.out.println("Number of successful entries: " + goodEntries);
			System.out.println("Number of failed entries: " + badEntries);

			//Statistics
			writer = new PrintWriter("statistics.txt", "UTF-8");
			writer.append("Number of entries: " + numberOfEntries);
			writer.append("\n");
			writer.append("Number of successful entries: " + goodEntries);
			writer.append("\n");
			writer.append("Number of failed entries: " + badEntries);
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public static boolean anyIndexIsEmpty(String[] currentLine) {
		for (int i = 0; i < currentLine.length; i++) {
			if (currentLine[i].isEmpty()) {
				return true;
			}
		}
		return false;
	}
}
