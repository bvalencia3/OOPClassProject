package project;

/**
 * The Repository class manages different types of bank accounts.
 * It provides methods for depositing and withdrawing funds
 * from Checking, Credit, and Savings accounts.
 */
public class Repository {
    private int accounts;       // Total number of accounts
    private int checkingAcc;    // Number of checking accounts
    private int creditAcc;      // Number of credit accounts
    private int savingsAcc;     // Number of savings accounts

    /**
     * Constructs a Repository with initialized account counts.
     */
    public Repository() {
        accounts = 0;
        checkingAcc = 0;
        creditAcc = 0;
        savingsAcc = 0;
    }

    /**
     * Withdraws a specified amount from a Checking account.
     *
     * @param c the Checking account from which to withdraw
     * @param amount the amount to withdraw
     */
    public void withdraw(Checking c, double amount) {
        // not yet implemented
    }

    /**
     * Withdraws a specified amount from a Credit account.
     *
     * @param c the Credit account from which to withdraw
     * @param amount the amount to withdraw
     */
    public void withdraw(Credit c, double amount) {
        // not yet implemented
    }

    /**
     * Withdraws a specified amount from a Savings account.
     *
     * @param s the Savings account from which to withdraw
     * @param amount the amount to withdraw
     */
    public void withdraw(Savings s, double amount) {
        // not yet implemented
    }

    /**
     * Deposits a specified amount into a Checking account.
     *
     * @param c the Checking account into which to deposit
     * @param amount the amount to deposit
     */
    public void deposit(Checking c, double amount) {
        // not yet implemented
    }

    /**
     * Deposits a specified amount into a Credit account.
     *
     * @param c the Credit account into which to deposit
     * @param amount the amount to deposit
     */
    public void deposit(Credit c, double amount) {
        // not yet implemented
    }

    /**
     * Deposits a specified amount into a Savings account.
     *
     * @param s the Savings account into which to deposit
     * @param amount the amount to deposit
     */
    public void deposit(Savings s, double amount) {
        // not yet implemented
    }

}
