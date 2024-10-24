
/**
 * @author Saul Gonzalez, Bruno Valencia, Gian Hernandez
 * The abstract Account class represents a bank account.
 * 
 */
public abstract class Account {
    protected double balance;

    /**
     * creates an account with a balance.
     * 
     * @param balance the starting balance of the account
     */
    public Account(double balance) {
        this.balance = balance;
    }

    /**
     * Gets the current balance of the account.
     * 
     * @return the balance of the account
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Deposits a specified amount into the account.
     * 
     * @param amount the amount to deposit
     */
    public void deposit(double amount) {
        balance += amount;
    }

    /**
     * Withdraws a specified amount from the account.
     * 
     * @param amount the amount to withdraw
     * @return true if the withdrawal is successful, false if there are insufficient funds
     */
    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    /**
     * Returns a string of the Account.
     * 
     * @return the balance of the account
     */
    @Override
    public String toString() {
        return "Balance: " + balance;
    }
}

