
/**
 * @author Saul Gonzalez, Bruno Valencia, Gian Hernandez
 *         The Savings class represents a savings account.
 */

public class Savings extends Account {

    /**
     * Creates a savings account with an starting balance.
     * 
     * @param balance the starting balance of the savings account
     */
    public Savings(double balance) {
        super(balance);
    }

    /**
     * Transfers an amount from this account to another.
     * 
     * @param targetAccount the target account to transfer funds to
     * @param amount        the amount to transfer
     * @return true if the transfer is successful, false otherwise
     */
    @Override
    public boolean transfer(BankOperations targetAccount, double amount) {
        if (withdraw(amount)) {
            targetAccount.deposit(amount);
            return true;
        }
        return false;
    }

    /**
     * Returns a string of the Savings account.
     * 
     * @return a string with account type and balance
     */
    @Override
    public String toString() {
        return "Savings Account - Balance: " + balance;
    }
}
