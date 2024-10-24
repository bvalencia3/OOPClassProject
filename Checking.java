
/**
 * The checking class represents a customers checking account
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
     * Returns a string of the Checking account.
     * 
     * @return a string with account type and balance
     */
    @Override
    public String toString() {
        return "Checking Account - " + super.toString();
    }
}

