
public class Savings extends Account {
    public Savings(double balance) {
        super(balance);
    }

    @Override
    public String toString() {
        return "Savings Account - " + super.toString();
    }
}
