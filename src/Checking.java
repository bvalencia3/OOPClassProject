
/**
 * @author Saul Gonzalez, Bruno Valencia, Gian Hernandez
 *         The checking class represents a customers checking account
 */

public class Checking extends Account {

    /**
     * Creates a checking account with an initial balance.
     * 
     * @param balance the initial balance of the checking account
     */
    public Checking(double balance) {
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
     * Returns a string of the Checking account.
     * 
     * @return a string with account type and balance
     */
    @Override
    public String toString() {
        return "Checking Account - Balance: " + balance;
    }
}
