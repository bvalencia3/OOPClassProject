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
    	try {
            c.withdrawal(amount);  // Call the withdrawal method of the Checking account
            System.out.println("Withdrawal successful from Checking account.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); //Prints error detail
        }
    }

    /**
     * Withdraws a specified amount from a Credit account.
     *
     * @param c the Credit account from which to withdraw
     * @param amount the amount to withdraw
     */
    public void withdraw(Credit c, double amount) {
    	try {
            c.withdrawal(amount);  // Call the withdrawal method of the Credit account
            System.out.println("Withdrawal successful from Credit account.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); //Prints error detail
        }
    }

    /**
     * Withdraws a specified amount from a Savings account.
     *
     * @param s the Savings account from which to withdraw
     * @param amount the amount to withdraw
     */
    public void withdraw(Savings s, double amount) {
    	try {
            s.withdrawal(amount);  // Call the withdrawal method of the Savings account
            System.out.println("Withdrawal of successful from Savings account.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); //Prints error detail
        }
    }

    /**
     * Deposits a specified amount into a Checking account.
     *
     * @param c the Checking account into which to deposit
     * @param amount the amount to deposit
     */
    public void deposit(Checking c, double amount) {
    	try {
            c.deposit(amount);  // Call the deposit method of the Checking account
            System.out.println("Deposit of successful to Checking account.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); //Prints error detail
        }
    }

    /**
     * Deposits a specified amount into a Credit account.
     *
     * @param c the Credit account into which to deposit
     * @param amount the amount to deposit
     */
    public void deposit(Credit c, double amount) {
    	try {
            c.makePayment(amount);  // Make a payment towards the credit balance
            System.out.println("Payment successful to Credit account.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); //Prints error detail
        }
    }

    /**
     * Deposits a specified amount into a Savings account.
     *
     * @param s the Savings account into which to deposit
     * @param amount the amount to deposit
     */
    public void deposit(Savings s, double amount) {
    	try {
            s.deposit(amount);  // Call the deposit method of the Savings account
            System.out.println("Deposit successful to Savings account.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); //Prints error detail
        }
    }
