package BankProject.src.project;

/**
 * Represents a checking account which extends the Account class.
 */
public class Checking extends Account {

    /**
     * Constructs a Checking account with the given account number and customer ID.
     *
     * @param accNum the account number
     * @param customerId the ID of the customer who owns the account
     */
    public Checking(long accNum, int customerId) {
        super(accNum, customerId);
    }

    
}
