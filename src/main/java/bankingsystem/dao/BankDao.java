package bankingsystem.dao;

import java.util.Scanner;

import bankingsystem.users.UserCrud;

public class BankDao {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("=================================");
		System.out.println("WELCOME TO BANK OF PUNE");
		System.out.println("=================================");

		boolean loop = true;
		while (loop) {
			System.out.println("1. Register User");
			System.out.println("2. Login User");
			System.out.println("3. User Exit");

			System.out.println("Enter your choice: ");
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				register();
				break;

			case 2:
				login();
				break;

			case 3: {
				loop = false;
				System.out.println("Thanks for visit!....");
				break;
			}

			default:
				System.out.println("Please choose appropriate option");
			}

		}

	}

	public static void register() {
		UserCrud.register();
	}

	public static void login() {
		UserCrud.login();
			
	}
}


