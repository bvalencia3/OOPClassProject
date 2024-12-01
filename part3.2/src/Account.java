
/**
 * @author Saul Gonzalez, Bruno Valencia, Gian Hernandez
 * 
 *         The abstract Account class represents a bank account.
 *         The account class will implement methods from the BankOperations
 *         Class
 */
public abstract class Account implements BankOperations {
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
     * @return
     */
    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    /**
     * Withdraws a specified amount from the account.
     * 
     * @param amount the amount to withdraw
     * @return true if the withdrawal is successful, false if there are insufficient
     *         funds
     */
    @Override
    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    /**
     * Transfers a specified amount from this account to a target account.
     * 
     * @param targetAccount the target account to transfer funds to
     * @param amount        the amount to transfer
     * @return true if the transfer is successful; false otherwise
     */
    @Override
    public abstract boolean transfer(BankOperations targetAccount, double amount);
}
