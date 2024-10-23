package project;

/**
 * Represents a credit account which extends the Account class.
 */
public class Credit extends Account {
    private double limit;      // Credit limit
    private double principal;  // Amount owed

    /**
     * Constructs a Credit account with the given account number, customer ID, and credit limit.
     *
     * @param accNum the account number
     * @param customerId the ID of the customer who owns the account
     * @param limit the credit limit for this account
     */
    public Credit(long accNum, int customerId, double limit) {
        super(accNum, customerId);
        this.limit = limit;
        this.principal = 0.0;
    }

    /**
     * Withdraws a specified amount from the credit account, increasing the principal owed.
     *
     * @param amount the amount to withdraw (borrow)
     */
    @Override
    public void withdrawal(double amount) {
        if (amount > 0 && (principal + amount) <= limit) {
            principal += amount;
        } else {
            throw new IllegalArgumentException("Withdrawal amount exceeds credit limit.");
        }
    }

    /**
     * Makes a payment towards the credit account, reducing the principal owed.
     *
     * @param amount the amount to pay
     */
    public void makePayment(double amount) {
        if (amount > 0) {
            principal -= amount;
            if (principal < 0) {
                principal = 0; // Prevents negative principal
            }
        } else {
            throw new IllegalArgumentException("Payment amount must be positive.");
        }
    }

    /**
     * Gets the current balance owed on the credit account.
     *
     * @return the principal amount owed
     */
    public double getPrincipal() {
        return principal;
    }

    /**
     * Gets the credit limit of the account.
     *
     * @return the credit limit
     */
    public double getLimit() {
        return limit;
    }
}
