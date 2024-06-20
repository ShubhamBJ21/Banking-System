package bankingsystem.accountmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import bankingsystem.connection.Connector;

public class AccountManager {

	static Scanner sc = new Scanner(System.in);

	public static void showBankOptions() {

		boolean loop = true;

		while (loop) {
			System.out.println("1. Open Account");
			System.out.println("2. Check Account");
			System.out.println("3. Credit Money");
			System.out.println("4. Debit Money");
			System.out.println("5. Transfer Money");
			System.out.println("6. Exit");

			System.out.println("Choose Your option: ");
			int option = sc.nextInt();
			sc.nextLine();

			switch (option) {
			case 1:
				openAccount();
				break;

			case 2:
				checkAccountDetails();
				break;

			case 3:
				creditMoney();
				break;

			case 4:
				debitMoney();
				break;

			case 5:
				transferMoney();
				break;

			case 6:
				loop = false;
				break;

			default:
				System.out.println("Please choose the right option");
				break;
			}
		}
	}

	public static void openAccount() {

		System.out.println("Enter your name: ");
		String fname = sc.nextLine();

		System.out.println("Enter your email: ");
		String email = sc.nextLine();

		System.out.println("Enter your initial balance: ");
		double balance = sc.nextDouble();
		sc.nextLine();

		System.out.println("Enter your security pin: ");
		int pin = sc.nextInt();
		sc.nextLine();

		Account account = new Account(fname, email, balance, pin);

		Connection connection = Connector.Connect();

		String query = "insert into account (fname, email, accountNumber, balance, pin) values (?, ?, ?, ?, ?)";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, fname);
			preparedStatement.setString(2, email);
			preparedStatement.setLong(3, account.getAccountNumber());
			preparedStatement.setDouble(4, balance);
			preparedStatement.setInt(5, pin);

			int result = preparedStatement.executeUpdate();

			if (result != 0) {
				System.out.println("Account created successfully!");
				System.out.println("Your account number is: " + account.getAccountNumber());
			} else {
				System.out.println("Account creation failed...");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void checkAccountDetails() {
		System.out.println("Enter your account number: ");
		long accountNumber = sc.nextLong();
		sc.nextLine();

		System.out.println("Enter your security pin: ");
		int securityPin = sc.nextInt();
		sc.nextLine();

		if (validatePin(accountNumber, securityPin)) {
			System.out.println("========================================");
			System.out.println("Account details:");
			System.out.println("Name: " + getName(accountNumber));
			System.out.println("Email: " + getEmail(accountNumber));
			System.out.println("Balance: " + getBalance(accountNumber));
			System.out.println("========================================");
		} else {
			System.out.println("Invalid account number or pin");
		}
	}

	public static boolean validatePin(long accountNumber, int pin) {
		Connection connection = Connector.Connect();
		String query = "select pin from account where accountNumber = ?";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, accountNumber);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return pin == resultSet.getInt("pin");
			}

			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static String getName(long accountNumber) {
		return getAccountDetail(accountNumber, "fname");
	}

	public static String getEmail(long accountNumber) {
		return getAccountDetail(accountNumber, "email");
	}

	public static double getBalance(long accountNumber) {
		Connection connection = Connector.Connect();
		String query = "select balance from account where accountNumber = ?";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, accountNumber);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return resultSet.getDouble("balance");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public static String getAccountDetail(long accountNumber, String column) {
		Connection connection = Connector.Connect();
		String query = "select " + column + " from account where accountNumber = ?";

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, accountNumber);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return resultSet.getString(column);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void creditMoney() {
		Connection connection = Connector.Connect();

		String deposit = "update account set balance = balance + ? where accountnumber = ?";

		System.out.println("Enter your account number: ");
		long accountNumber = sc.nextLong();
		sc.nextLine();

		System.out.println("Enter your pin: ");
		int pin = sc.nextInt();
		sc.nextLine();

		if (validatePin(accountNumber, pin)) {
			System.out.println("Enter amount for deposit");
			double amount = sc.nextDouble();
			sc.nextLine();

			try {
				connection.setAutoCommit(false);

				PreparedStatement preparedStatement = connection.prepareStatement(deposit);
				preparedStatement.setDouble(1, amount);
				preparedStatement.setLong(2, accountNumber);
				preparedStatement.executeUpdate();

				connection.commit();
				System.out.println("Balance credited successfully");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Balance not credited");
			}

		} else {
			System.out.println("Invalid account number or pin");
		}
	}

	public static void debitMoney() {
		Connection connection = Connector.Connect();

		String withdraw = "update account set balance = balance - ? where accountnumber = ?";

		System.out.println("Enter your account number: ");
		long accountNumber = sc.nextLong();
		sc.nextLine();

		System.out.println("Enter your pin: ");
		int pin = sc.nextInt();
		sc.nextLine();

		if (validatePin(accountNumber, pin)) {
			System.out.println("Enter amount for withdraw");
			double amount = sc.nextDouble();
			sc.nextLine();

			try {
				connection.setAutoCommit(false);

				PreparedStatement preparedStatement = connection.prepareStatement(withdraw);
				preparedStatement.setDouble(1, amount);
				preparedStatement.setLong(2, accountNumber);
				preparedStatement.executeUpdate();

				connection.commit();
				System.out.println("Balance withdraw successfully");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Balance not withdraw");
			}

		} else {
			System.out.println("Invalid account number or pin");
		}

	}

	public static void transferMoney() {
		Connection connection = Connector.Connect();
		String withdraw = "update account set balance = balance - ? where accountnumber = ?";
		String deposit = "update account set balance = balance + ? where accountnumber = ?";

		System.out.println("Enter your account number: ");
		long withdrawAccountNumber = sc.nextLong();
		sc.nextLine();

		System.out.println("Enter your pin: ");
		int pin = sc.nextInt();
		sc.nextLine();
		
		if (validatePin(withdrawAccountNumber, pin)) {
			
			System.out.println("Enter the balance you want to withdraw");
			double withdrawBalance = sc.nextDouble();
			sc.nextLine();

			System.out.println("Enter Amount Transfer account number: ");
			long depositAccountNumber = sc.nextLong();
			sc.nextLine();
			
			System.out.println("Enter the balance you want to deposit");
			double depositBalance = sc.nextDouble();
			sc.nextLine();
			
			try {
				connection.setAutoCommit(false);
				
				PreparedStatement withDrawStatement = connection.prepareStatement(withdraw);

				withDrawStatement.setDouble(1, withdrawBalance);
				withDrawStatement.setLong(2, withdrawAccountNumber);
				withDrawStatement.executeUpdate();

				PreparedStatement depositStatement = connection.prepareStatement(deposit);

				depositStatement.setDouble(1, depositBalance);
				depositStatement.setLong(2, depositAccountNumber);
				depositStatement.executeUpdate();

				connection.commit();
				System.out.println("Money Transfered succesfully");
			}catch(Exception e) {
				System.out.println("Money can't Transfer");
			}
			
		}else {
			System.out.println("Invalid account number or pin");
		}

	}

}