package bankingsystem.users;

import bankingsystem.accountmanager.AccountManager;
import bankingsystem.connection.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserCrud {
	static Scanner sc = new Scanner(System.in);

	public static void register() {
		
		System.out.println("Enter your name: ");
		String name = sc.nextLine();
		name = name.trim();
		
		System.out.println("Enter your email: ");
		String email = sc.nextLine();
		email = email.trim();

		System.out.println("Enter your Phone number: ");
		long phone = sc.nextLong();
		sc.nextLine();

		System.out.println("Enter your address: ");
		String address = sc.nextLine();
		address = address.trim();

		System.out.println("Enter your password");
		String password = sc.nextLine();
		password = password.trim();
		//checkPassword(password);//------------------------

		Connection connection = Connector.Connect();

		String query = "Insert into user values(?, ?, ?, ?, ?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, name);
			preparedStatement.setString(2, email);
			preparedStatement.setLong(3, phone);
			preparedStatement.setString(4, address);
			preparedStatement.setString(5, password);

			int result = preparedStatement.executeUpdate();

			if (result != 0) {
				System.out.println("Register successful!...");
			} else {
				System.out.println("Registration fail!....");
			}

			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			try {
				connection.close();
				System.out.println("Connection closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void login() {
		System.out.println("Enter your email: ");
		String email = sc.nextLine();
		email = email.trim();
						
		String dbpassword = getPassword(email);
		
		if(dbpassword != null) {
			
			System.out.println("Enter your password: ");
			String password = sc.nextLine();
			password = password.trim();
			
			if(dbpassword.equals(password)) {
				System.out.println("Login successfull!...");
				
				AccountManager.showBankOptions();
			}	
			else {
				System.out.println("login fail!....");
			}
		}else {
			System.out.println("User not exist!...");
			System.out.println("Please Register first");
		}
	}
	
	public static String getPassword(String email) {
		Connection connection = Connector.Connect();
		
		String query = "Select password from user where email = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, email);
			
			ResultSet set = preparedStatement.executeQuery();
			
			String password = null;
			
			while(set.next()) {
				password = set.getString("password");
				
				if(password!=null) {
					break;
				}
			}
			
			return password;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Password not found in database");
			return null;
		}
	}
	
	public static void checkPassword(String password) {
		
	}
}
