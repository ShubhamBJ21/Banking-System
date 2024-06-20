# Banking System Project

A console-based banking system application implemented in Java using JDBC. This project demonstrates the use of prepared statements, callable statements, and stored procedures for database operations.

## Features

- Create new bank accounts
- View account details
- Deposit and withdraw funds
- Transfer funds between accounts
- View transaction history

## Requirements

- Java Development Kit (JDK) 8 or higher
- Maven
- MySQL or any other relational database

## Database Setup

1. Create a database named `banking_system`.
2. Run the SQL scripts in `src/main/resources/db/create_tables.sql` to create the necessary tables.
3. Run the SQL scripts in `src/main/resources/db/stored_procedures.sql` to create the stored procedures.

## Installation

1. Clone the repository:

    ```sh
    git clone https://github.com/ShubhamBJ21/banking-system.git
    ```

2. Navigate to the project directory:

    ```sh
    cd banking-system
    ```

3. Build the project using Maven:

    ```sh
    mvn clean install
    ```

## Configuration

1. Update the database connection details in `DBConnection.java`:

    ```java
    private static final String URL = "jdbc:mysql://localhost:3306/banking_system";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    ```

## SQL Scripts

- **create_tables.sql**: Contains SQL scripts for creating necessary database tables.
- **stored_procedures.sql**: Contains SQL scripts for creating stored procedures used in the application.

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -m 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.



