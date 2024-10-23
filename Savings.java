package project;

/**
 * Represents a savings account which extends the Account class.
 */
public class Savings extends Account {
    private double interestRate; // Interest rate as a percentage

    /**
     * Constructs a Savings account with the given account number, customer ID, and interest rate.
     *
     * @param accNum the account number
     * @param customerId the ID of the customer who owns the account
     * @param interestRate the annual interest rate for the savings account
     */
    public Savings(long accNum, int customerId, double interestRate) {
        super(accNum, customerId);
        this.interestRate = interestRate;
    }

    /**
     * Deposits a specified amount into the savings account.
     *
     * @param amount the amount to deposit
     */
    @Override
    public void deposit(double amount) {
        super.deposit(amount); // Call the deposit method from Account class
    }

    /**
     * Withdraws a specified amount from the savings account.
     *
     * @param amount the amount to withdraw
     */
    @Override
    public void withdrawal(double amount) {
        if (amount > 0 && amount <= getBalance()) {
            super.withdrawal(amount); // Call the withdrawal method from Account class
        } else {
            throw new IllegalArgumentException("Withdrawal amount must be positive and cannot exceed the balance.");
        }
    }

    /**
     * Calculates the interest earned on the current balance.
     *
     * @return the interest earned
     */
    public double calculateInterest() {
        return getBalance() * (interestRate / 100);
    }

    /**
     * Gets the interest rate of the savings account.
     *
     * @return the interest rate
     */
    public double getInterestRate() {
        return interestRate;
    }

    /**
     * Sets the interest rate of the savings account.
     *
     * @param interestRate the new interest rate
     */
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
