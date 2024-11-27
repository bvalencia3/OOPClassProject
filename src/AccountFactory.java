public class AccountFactory {

    /**
     * Creates an account based on a string identifier.
     *
     * @param type        the type of account to create ("Checking", "Savings",
     *                    "Credit")
     * @param balance     the initial balance of the account
     * @param creditLimit the credit limit (only for Credit accounts)
     * @return the created Account object
     */
    public static Account createAccount(String type, double balance, double creditLimit) {
        switch (type.toLowerCase()) {
            case "checking":
                return new Checking(balance);
            case "savings":
                return new Savings(balance);
            case "credit":
                return new Credit(balance, creditLimit);
            default:
                throw new IllegalArgumentException("Invalid account type: " + type);
        }
    }
}
