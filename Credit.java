
public class Credit extends Account {
    private double creditLimit;

    public Credit(double balance, double creditLimit) {
        super(balance);
        this.creditLimit = creditLimit;
    }

    public boolean withdraw(double amount) {
        if (balance + creditLimit >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Credit Account - " + super.toString() + ", Credit Limit: " + creditLimit;
    }
}
