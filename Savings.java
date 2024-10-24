
/**
 * @author Saul Gonzalez, Bruno Valencia, Gian Hernandez
 * The Savings class represents a savings account.
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
     * Returns a string of the Savings account.
     * 
     * @return a string with account type and balance
     */
    @Override
    public String toString() {
        return "Savings Account - " + super.toString();
    }
}

