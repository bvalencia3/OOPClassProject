
/**
 * @author Saul Gonzalez, Bruno Valencia, Gian Hernandez
 * 
 *         Interface representing basic bank operations for accounts.
 *         Ensures each account type supports deposit, withdrawal, transfer, and
 *         balance
 *         retrieval.
 */
public interface BankOperations {

    // Bank operation Interface - Acts like a "Control" where it makes sure the
    // checkings, savings, and credit
    // have the same basic functions. Yet each class will handle the operation
    // slightly different

    /**
     * Deposits a specified amount into the account.
     * 
     * @param amount the amount to deposit
     */
    void deposit(double amount);

    /**
     * Withdraws a specified amount from the account.
     * 
     * @param amount the amount to withdraw
     * @return true if the withdrawal is successful, false otherwise
     */
    boolean withdraw(double amount);

    /**
     * Transfers a specified amount to another account.
     * 
     * @param targetAccount the target account to receive the transfer
     * @param amount        the amount to transfer
     * @return true if the transfer is successful, false otherwise
     */
    boolean transfer(BankOperations targetAccount, double amount);

    /**
     * Retrieves the current balance of the account.
     * 
     * @return the balance of the account
     */
    double getBalance();
}
