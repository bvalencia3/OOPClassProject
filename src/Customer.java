
/**
 * @author Saul Gonzalez, Bruno Valencia, Gian Hernandez
 *         The Customer class thats shows the user with the checking, savings,
 *         and credit accounts. Extends the Person class to include accounts for
 *         banking transactions.
 */
public class Customer extends Person {
    private Checking checkingAccount;
    private Savings savingsAccount;
    private Credit creditAccount;

    /**
     * Creates a Customer with its accounts.
     * 
     * @param name     the name of the customer
     * @param id       the ID of the customer
     * @param checking the checking account of the customer
     * @param savings  the savings account of the customer
     * @param credit   the credit account of the customer
     */
    public Customer(String name, String id, Checking checking, Savings savings, Credit credit) {
        super(name, id);
        this.checkingAccount = checking;
        this.savingsAccount = savings;
        this.creditAccount = credit;
    }

    /**
     * Gets the checking account of the customer
     * 
     * @return the checking account
     */
    public Checking getCheckingAccount() {
        return checkingAccount;
    }

    /**
     * Gets the savings account of the customer
     * 
     * @return the savings account
     */
    public Savings getSavingsAccount() {
        return savingsAccount;
    }

    /**
     * Gets the credit account of the customer
     * 
     * @return the credit account
     */
    public Credit getCreditAccount() {
        return creditAccount;
    }

    /**
     * Returns a string of the Customer.
     * 
     * @return the name, ID, and account details of the customer
     */
    @Override
    public String toString() {
        return super.toString() + "\\n" + checkingAccount + "\\n" + savingsAccount + "\\n" + creditAccount;
    }
}
