package bankingsystem.accountmanager;

public class Account {

    private String fName;
    private String email;
    private long accountNumber;
    private double balance;
    private int pin;
    private static long nextAccountNumber = 1000000000100L;

    public Account() {
        this.accountNumber = nextAccountNumber++;
    }

    public Account(String fName, String email, double balance, int pin) {
        this();
        this.fName = fName;
        this.email = email;
        this.balance = balance;
        this.pin = pin;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
