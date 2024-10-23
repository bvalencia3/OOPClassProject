package project;

/**
 * Abstract class representing a generic bank account.
 */
public abstract class Account {
    private long accNum;       // Account number
    private double balance;     // Account balance
    private int customerId;     // Customer ID

    /**
     * Constructs an Account with the given account number and customer ID.
     *
     * @param accNum the account number
     * @param customerId the ID of the customer who owns the account
     */
    public Account(long accNum, int customerId) {
        this.accNum = accNum;
        this.customerId = customerId;
        this.balance = 0.0;
    }

    /**
     * Deposits a specified amount into the account.
     *
     * @param amount the amount to deposit
     */
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
    }

    /**
     * Withdraws a specified amount from the account.
     *
     * @param amount the amount to withdraw
     */
    public void withdrawal(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Withdrawal amount must be positive and cannot exceed the balance.");
        }
    }

    /**
     * Gets the current balance of the account.
     *
     * @return the current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Gets the account number of the account.
     *
     * @return the account number
     */
    public long getAccNum() {
        return accNum;
    }

    /**
     * Gets the customer ID associated with the account.
     *
     * @return the customer ID
     */
    public int getCustomerId() {
        return customerId;
    }
}
