
/**
 * The Credit class with a credit balance and limit
 */
public class Credit extends Account {
    private double creditLimit;

    /**
     * Creates a credit account with an starting balance and credit limit.
     * 
     * @param balance the starting balance of the credit account
     * @param creditLimit the max credit limit for the account
     */
    public Credit(double balance, double creditLimit) {
        super(balance);
        this.creditLimit = creditLimit;
    }

    /**
     * Withdraws an amount from the credit account
     * 
     * @param amount the amount to withdraw
     * @return true if the withdrawal is successful, false if the limit is exceeded
     */
    @Override
    public boolean withdraw(double amount) {
        if (balance + creditLimit >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    /**
     * Returns a string of the Credit account.
     * 
     * @return a string with account type, balance, and credit limit
     */
    @Override
    public String toString() {
        return "Credit Account - " + super.toString() + ", Credit Limit: " + creditLimit;
    }
}

