package bankingsystem.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {

	public static Connection Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//System.out.println("Driver loaded successfully!...");
		} catch (Exception e) {
			System.out.println("Driver is not loaded");
		}

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system", "root",
					"root");
			//System.out.println("Connection established!...");
			return connection;
		} catch (Exception e) {
			System.out.println("Connection Fail");
			return null;
		}
	}
}
