

public class Customer extends Person {
    private Checking checkingAccount;
    private Savings savingsAccount;
    private Credit creditAccount;

    public Customer(String name, String id, Checking checking, Savings savings, Credit credit) {
        super(name, id);
        this.checkingAccount = checking;
        this.savingsAccount = savings;
        this.creditAccount = credit;
    }

    public Checking getCheckingAccount() {
        return checkingAccount;
    }

    public Savings getSavingsAccount() {
        return savingsAccount;
    }

    public Credit getCreditAccount() {
        return creditAccount;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + checkingAccount + "\n" + savingsAccount + "\n" + creditAccount;
    }
}
